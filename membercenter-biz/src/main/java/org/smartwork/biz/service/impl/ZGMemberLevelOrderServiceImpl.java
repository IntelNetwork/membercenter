package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.enums.DeadUnitEnum;
import org.forbes.comm.enums.YesNoEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.utils.DateUtils;
import org.smartwork.biz.service.IZGMemberLevelOrderService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.dal.entity.ZGMemberLevel;
import org.smartwork.dal.entity.ZGMemberLevelOrder;
import org.smartwork.dal.mapper.ZGMemberLevelMapper;
import org.smartwork.dal.mapper.ZGMemberLevelOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;

@Service
public class ZGMemberLevelOrderServiceImpl extends ServiceImpl<ZGMemberLevelOrderMapper, ZGMemberLevelOrder> implements IZGMemberLevelOrderService {

    private  String format = "ML%s";

    @Value("${forbes.grade.exp.time}")
    Integer gradeExpTime;

    @Autowired
    ZGMemberLevelMapper memberLevelMapper;


    /***
     *
     * @param memberLevel
     * @return
     * @throws ForbesException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ZGMemberLevelOrder createLevelOrder(ZGMemberLevel memberLevel)
            throws ForbesException{
        try {
            SysUser sysUser = UserContext.getSysUser();
            List<ZGMemberLevelOrder> memberLevelOrderList = baseMapper.selectList(new QueryWrapper<ZGMemberLevelOrder>()
                    .eq("member_id",sysUser.getId()));
            long effectCount = memberLevelOrderList.stream()
                    .filter(levelOrder -> YesNoEnum.NO.getCode().equalsIgnoreCase(String.valueOf(levelOrder.getTakeEffect()))).count();
            if(effectCount > 0 ){
                throw new ForbesException(MemberBizResultEnum.TAKE_EFFECT.getBizCode(),MemberBizResultEnum.TAKE_EFFECT.getBizMessage());
            }
            long existsEffectCount = memberLevelOrderList.stream()
                    .filter(levelOrder -> YesNoEnum.YES.getCode().equalsIgnoreCase(String.valueOf(levelOrder.getTakeEffect()))
                            && levelOrder.getEndDate().after(new Date())
                            && levelOrder.getBid().equalsIgnoreCase(memberLevel.getBid())).count();
            if(existsEffectCount > 0){
                throw new ForbesException(MemberBizResultEnum.APPLY_TAKE_EFFECT.getBizCode(),MemberBizResultEnum.APPLY_TAKE_EFFECT.getBizMessage());
            }
            long existsCount = memberLevelOrderList.stream()
                    .filter(levelOrder -> YesNoEnum.YES.getCode().equalsIgnoreCase(String.valueOf(levelOrder.getTakeEffect()))
                            && levelOrder.getEndDate().after(new Date())
                            && !levelOrder.getBid().equalsIgnoreCase(memberLevel.getBid())).count();
            if(existsCount > 0 ){
                throw new ForbesException(MemberBizResultEnum.LEVEL_CHANGE.getBizCode(),MemberBizResultEnum.LEVEL_CHANGE.getBizMessage());
            }
            ZGMemberLevelOrder memberLevelOrder = new ZGMemberLevelOrder();
            memberLevelOrder.setMemberId(sysUser.getId());
            memberLevelOrder.setMemberName(sysUser.getRealname());
            memberLevelOrder.setMlOrderNo(String.format(format,DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS")));
            memberLevelOrder.setDeadline(memberLevel.getDeadline());
            memberLevelOrder.setDeadUnit(memberLevel.getDeadUnit());
            memberLevelOrder.setTakeEffect(Integer.parseInt(YesNoEnum.NO.getCode()));
            memberLevelOrder.setPayAmount(memberLevel.getCost());
            memberLevelOrder.setBid(memberLevel.getBid());
            baseMapper.insert(memberLevelOrder);
        }catch (Exception e){
            throw  e;
        }
        return  null;
    }

    /***
     * 会员等级变更
     * @param memberLevel
     * @return
     * @throws ForbesException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ZGMemberLevelOrder changeLevelOrder(ZGMemberLevel memberLevel) throws ForbesException{
        try {
            SysUser sysUser = UserContext.getSysUser();
            ZGMemberLevelOrder memberLevelOrder = baseMapper.selectOne(new QueryWrapper<ZGMemberLevelOrder>()
                    .eq("member_id",sysUser.getId())
                    .eq("take_effect",YesNoEnum.YES.getCode()));
            if(ConvertUtils.isNotEmpty(memberLevelOrder)){
                throw new ForbesException(MemberBizResultEnum.DID_NOT_ORDER.getBizCode(),MemberBizResultEnum.DID_NOT_ORDER.getBizMessage());
            }
            Date currentDate = new Date();
            if(DateUtils.getDifDay(currentDate,memberLevelOrder.getEndDate()) > gradeExpTime){
                throw new ForbesException(MemberBizResultEnum.MEMBER_LEVEL_EXP_TIME.getBizCode(),MemberBizResultEnum.MEMBER_LEVEL_EXP_TIME.getBizMessage());
            }
            ZGMemberLevel oldMemberLevel = memberLevelMapper.selectOne(new QueryWrapper<ZGMemberLevel>().eq("bid",memberLevelOrder.getBid()));
            if(oldMemberLevel.getOrders() > memberLevel.getOrders()){
                throw new ForbesException(MemberBizResultEnum.MEMBER_LEVEL_LOW.getBizCode(),MemberBizResultEnum.MEMBER_LEVEL_LOW.getBizMessage());

            }
            ZGMemberLevelOrder oldMemberLevelOrder = new ZGMemberLevelOrder();
            oldMemberLevelOrder.setMemberId(sysUser.getId());
            oldMemberLevelOrder.setMemberName(sysUser.getRealname());
            oldMemberLevelOrder.setMlOrderNo(String.format(format,DateFormatUtils.format(new Date(),"yyyyMMddHHmmssSSS")));
            oldMemberLevelOrder.setDeadline(memberLevel.getDeadline());
            oldMemberLevelOrder.setDeadUnit(memberLevel.getDeadUnit());
            oldMemberLevelOrder.setTakeEffect(Integer.parseInt(YesNoEnum.NO.getCode()));
            oldMemberLevelOrder.setPayAmount(memberLevel.getCost().subtract(oldMemberLevel.getCost()));
            oldMemberLevelOrder.setBid(memberLevel.getBid());
            oldMemberLevelOrder.setBeforeBid(memberLevelOrder.getBid());
            baseMapper.insert(oldMemberLevelOrder);
        }catch (Exception e){
            throw  e;
        }
        return  null;
    }


    /***
     *
     * @param memberLevelOrder
     * @throws ForbesException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void takeEffectMemberLevelOrder(ZGMemberLevelOrder memberLevelOrder)
            throws ForbesException{
        try {
            String beforeBid =  memberLevelOrder.getBeforeBid();
            String bid = memberLevelOrder.getBid();
            Integer deadLine = memberLevelOrder.getDeadline();
            String deadUnit = memberLevelOrder.getDeadUnit();
            Long memberId = memberLevelOrder.getMemberId();
            Date currentDate = new Date();
            Date endDate = null;
            // 前订单
            ZGMemberLevelOrder beforeMemberLevelOrder = baseMapper.selectOne(new QueryWrapper<ZGMemberLevelOrder>()
                    .eq("member_id",memberId)
                    .eq("bid",beforeBid)
                    .eq("take_effect",YesNoEnum.YES.getCode()));
            Integer hasDay = DateUtils.getDifDay(beforeMemberLevelOrder.getStartDate(),currentDate);
            if(DeadUnitEnum.MONTH.equals(deadUnit)){
                endDate  = org.apache.commons.lang.time.DateUtils.addMonths(currentDate,deadLine);
            }
            if(DeadUnitEnum.QUARTER.equals(deadUnit)){
                endDate  = org.apache.commons.lang.time.DateUtils.addMonths(currentDate,deadLine*3);
            }
            if(DeadUnitEnum.YEAR.equals(deadUnit)){
                endDate  = org.apache.commons.lang.time.DateUtils.addYears(currentDate,deadLine);
            }
            endDate = org.apache.commons.lang.time.DateUtils.addDays(endDate,-hasDay);
            baseMapper.update(null,new UpdateWrapper<ZGMemberLevelOrder>()
                    .set("start_date",currentDate)
                    .set("end_date",endDate)
                    .set("take_effect",YesNoEnum.YES.getCode())
                    .eq("id",memberLevelOrder.getId()));
            /***更新旧值**/
            baseMapper.update(null,new UpdateWrapper<ZGMemberLevelOrder>()
                    .set("end_date",currentDate)
                    .set("after_bid",bid)
                    .set("take_effect",YesNoEnum.NO.getCode())
                    .eq("id",beforeMemberLevelOrder.getId()));
        }catch (Exception e){
            throw e;
        }
    }
}