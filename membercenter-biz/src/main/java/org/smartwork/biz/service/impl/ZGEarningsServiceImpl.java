package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGEarningsService;
import org.smartwork.dal.entity.ZGEarnings;
import org.smartwork.dal.mapper.ZGEarningsMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGEarningsServiceImpl extends ServiceImpl<ZGEarningsMapper, ZGEarnings> implements IZGEarningsService {
}