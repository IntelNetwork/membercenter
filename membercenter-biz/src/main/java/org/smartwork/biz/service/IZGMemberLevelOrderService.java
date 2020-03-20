package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.exception.ForbesException;
import org.smartwork.dal.entity.ZGMemberLevel;
import org.smartwork.dal.entity.ZGMemberLevelOrder;

public interface IZGMemberLevelOrderService extends IService<ZGMemberLevelOrder> {


    /***
     * 创建等级订单
     * @param memberLevel
     * @return
     * @throws ForbesException
     */
    ZGMemberLevelOrder createLevelOrder(ZGMemberLevel memberLevel) throws ForbesException;
}