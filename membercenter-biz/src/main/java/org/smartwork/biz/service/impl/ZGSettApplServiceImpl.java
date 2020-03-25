package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.dal.entity.ZGEarnings;
import org.smartwork.dal.entity.ZGSettAppl;
import org.smartwork.dal.mapper.ZGEarningsMapper;
import org.smartwork.dal.mapper.ZGSettApplMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ZGSettApplServiceImpl extends ServiceImpl<ZGSettApplMapper, ZGSettAppl> implements IZGSettApplService {


    @Autowired
    ZGEarningsMapper  earningsMapper;
    @Autowired
    IMchInfoService   mchInfoService;



    /***
     * 申请结算
     * @param settAppl
     * @throws ForbesException
     */
    @Transactional(rollbackFor = {Exception.class})
    public void applySett(ZGSettAppl settAppl) throws ForbesException{
        SysUser sysUser = UserContext.getSysUser();
        ZGEarnings earnings = earningsMapper.selectOne(new QueryWrapper<ZGEarnings>().eq("user_id",sysUser.getId()));
        if(ConvertUtils.isEmpty(earnings)){
            throw new ForbesException(MemberBizResultEnum.NOT_AVAILABLE_BALANCE.getBizCode(),MemberBizResultEnum.NOT_AVAILABLE_BALANCE.getBizMessage());
        }
        if(BigDecimal.ZERO.compareTo(settAppl.getAmount()) > 0){
            throw new ForbesException(MemberBizResultEnum.ZERO_AMOUNT.getBizCode(),MemberBizResultEnum.ZERO_AMOUNT.getBizMessage());
        }
        if(earnings.getAmount().compareTo(settAppl.getAmount()) < 0){
            throw new ForbesException(MemberBizResultEnum.DEF_AVAILABLE_BALANCE.getBizCode(),MemberBizResultEnum.DEF_AVAILABLE_BALANCE.getBizMessage());
        }
    }
}