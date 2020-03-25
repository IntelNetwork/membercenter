package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.exception.ForbesException;
import org.smartwork.dal.entity.ZGSettAppl;

public interface IZGSettApplService extends IService<ZGSettAppl> {


    /***
     * 申请结算
     * @param settAppl
     * @throws ForbesException
     */
    void applySett(ZGSettAppl settAppl) throws ForbesException;
}