package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.smartwork.dal.entity.ZGEarnings;
import org.smartwork.dal.entity.ZGSettAppl;
import org.smartwork.dal.mapper.ZGEarningsMapper;
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
        settAppl.setApplOrderNo(String.format(format,DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS")));
        baseMapper.insert(settAppl);
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
}