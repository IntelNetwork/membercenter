package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.enums.ReviewStatusEnum;
import org.smartwork.comm.enums.SettlStatusEnum;
import org.smartwork.comm.enums.SourceDataTypeEnum;
import org.smartwork.dal.entity.ZGEarnings;
import org.smartwork.dal.entity.ZGRevenueRecord;
import org.smartwork.dal.entity.ZGSettAppl;
import org.smartwork.dal.mapper.ZGEarningsMapper;
import org.smartwork.dal.mapper.ZGRevenueRecordMapper;
import org.smartwork.dal.mapper.ZGSettApplMapper;
import org.smartwork.dal.mapper.ext.ZGSettApplExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class ZGSettApplServiceImpl extends ServiceImpl<ZGSettApplMapper, ZGSettAppl> implements IZGSettApplService {


    @Autowired
    ZGEarningsMapper  earningsMapper;
    @Autowired
    ZGSettApplExtMapper settApplExtMapper;
    @Autowired
    ZGRevenueRecordMapper revenueRecordMapper;

    String TITLE_FORMAT = "%s会员%s申请体现:%s";
    private  String format = "TX%s";
    @Value("${smartwork.transfer.retry-count}")
    private  Integer retryCount;



    /***
     * 申请结算
     * @param settAppl
     * @throws ForbesException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void applySett(ZGSettAppl settAppl, String mchId, BigDecimal reflectPoints) throws ForbesException{
        SysUser sysUser = UserContext.getSysUser();
        ZGEarnings earnings = earningsMapper.selectOne(new QueryWrapper<ZGEarnings>().eq("user_id",sysUser.getId()));
        if(ConvertUtils.isEmpty(earnings)){
            throw new ForbesException(MemberBizResultEnum.NOT_AVAILABLE_BALANCE.getBizCode(),MemberBizResultEnum.NOT_AVAILABLE_BALANCE.getBizMessage());
        }
        if(earnings.getAmount().compareTo(settAppl.getAmount()) < 0){
            throw new ForbesException(MemberBizResultEnum.DEF_AVAILABLE_BALANCE.getBizCode(),MemberBizResultEnum.DEF_AVAILABLE_BALANCE.getBizMessage());
        }
        Date currentDate = new Date();
        settAppl.setTitle(String.format(TITLE_FORMAT, DateFormatUtils.format(currentDate,"yyyyMMddHH"),sysUser.getRealname(),settAppl.getAmount().setScale(2, RoundingMode.UP)));
        settAppl.setMchId(mchId);
        settAppl.setReviewStatus(ReviewStatusEnum.AUDIT.getCode());
        settAppl.setSettlStatus(SettlStatusEnum.NO_SETTL.getCode());
        BigDecimal amount = settAppl.getAmount();
        BigDecimal reflectPointAmount = amount.multiply(reflectPoints);
        BigDecimal actualAmount = amount.subtract(reflectPointAmount);
        settAppl.setPlatDedAmount(reflectPointAmount);
        settAppl.setActualAmount(actualAmount);
        settAppl.setUserId(sysUser.getId());
        settAppl.setUserName(sysUser.getUsername());
        settAppl.setRetryCount(retryCount);
        settAppl.setFailureCount(0);
        settAppl.setEarningsAmount(earnings.getAmount());
        settAppl.setApplOrderNo(String.format(format,DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS")));
        baseMapper.insert(settAppl);
        /**扣减余额**/
       BigDecimal earningsAmount =  earnings.getAmount();
       BigDecimal afterAmount = earningsAmount.subtract(amount);
        earningsMapper.update(null,new UpdateWrapper<ZGEarnings>()
                .set("amount",afterAmount)
                .set("before_amount",earningsAmount)
                .set("have_time",new Date())
                .eq("user_id",sysUser.getId()));
    }


    /***查询待转账数据
     * @param page
     * @param reviewStatus
     * @param settlStatus
     * @return
     */
    @Override
    public IPage<ZGSettAppl> pageTransfer(IPage<ZGSettAppl> page, String reviewStatus, String settlStatus){
        return  settApplExtMapper.pageTransfer(page,reviewStatus,settlStatus);
    }


    /***
     * 转账成功
     * @param mchOrderNo
     * @throws ForbesException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferSuccess(String mchOrderNo) throws ForbesException{
        ZGSettAppl settAppl =  baseMapper.selectOne(new QueryWrapper<ZGSettAppl>().eq("appl_order_no",mchOrderNo));
        baseMapper.update(null,new UpdateWrapper<ZGSettAppl>()
                .set("settl_status",SettlStatusEnum.HAS_SETTL.getCode())
                .eq("appl_order_no",mchOrderNo));
        Long   userId = settAppl.getUserId();
        BigDecimal earningsAmount = settAppl.getEarningsAmount();
        ZGRevenueRecord revenueRecord = new ZGRevenueRecord();
        revenueRecord.setUserId(userId);
        revenueRecord.setUserName(settAppl.getUserName());
        revenueRecord.setSourceTitle(settAppl.getTitle());
        revenueRecord.setDataId(settAppl.getId());
        revenueRecord.setDataType(SourceDataTypeEnum.IN_SETTL.getCode());
        revenueRecord.setChangeAmount(settAppl.getAmount().negate());
        revenueRecord.setBeforeAmount(earningsAmount);
        revenueRecord.setAfterAmount(earningsAmount.subtract(settAppl.getAmount()));
        revenueRecordMapper.insert(revenueRecord);
    }
}