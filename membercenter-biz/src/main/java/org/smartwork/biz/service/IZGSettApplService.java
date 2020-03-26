package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.exception.ForbesException;
import org.smartwork.dal.entity.ZGSettAppl;

import java.math.BigDecimal;

public interface IZGSettApplService extends IService<ZGSettAppl> {


    /***
     * 申请结算
     * @param settAppl
     * @param mchId
     * @param reflectPoints
     * @throws ForbesException
     */
    void applySett(ZGSettAppl settAppl, String mchId, BigDecimal reflectPoints) throws ForbesException;
}