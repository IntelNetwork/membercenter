package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.enums.YesNoEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.SysUser;
import org.smartwork.biz.service.IZGMemberLevelOrderService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.dal.entity.ZGMemberLevel;
import org.smartwork.dal.entity.ZGMemberLevelOrder;
import org.smartwork.dal.mapper.ZGMemberLevelOrderMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZGMemberLevelOrderServiceImpl extends ServiceImpl<ZGMemberLevelOrderMapper, ZGMemberLevelOrder> implements IZGMemberLevelOrderService {

    private  String format = "ML%s";

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
                            && levelOrder.getEndDate().after(new Date())).count();
            if(existsEffectCount > 0){
                throw new ForbesException(MemberBizResultEnum.APPLY_TAKE_EFFECT.getBizCode(),MemberBizResultEnum.APPLY_TAKE_EFFECT.getBizMessage());
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
}