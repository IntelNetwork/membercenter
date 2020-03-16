package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGMemberLevelOrderService;
import org.smartwork.dal.entity.ZGMemberLevelOrder;
import org.smartwork.dal.mapper.ZGMemberLevelOrderMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGMemberLevelOrderServiceImpl extends ServiceImpl<ZGMemberLevelOrderMapper, ZGMemberLevelOrder> implements IZGMemberLevelOrderService {
}