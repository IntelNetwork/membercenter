package org.smartwork.biz.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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


    /***查询待转账数据
     * @param page
     * @param reviewStatus
     * @param settlStatus
     * @return
     */
    IPage<ZGSettAppl> pageTransfer(IPage<ZGSettAppl> page,String reviewStatus,String settlStatus);

    /***
     * 转账成功
     * @param mchOrderNo
     * @throws ForbesException
     */
    void transferSuccess(String mchOrderNo) throws ForbesException;
}