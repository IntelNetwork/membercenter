package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGRevenueRecordService;
import org.smartwork.dal.entity.ZGRevenueRecord;
import org.smartwork.dal.mapper.ZGRevenueRecordMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGRevenueRecordServiceImpl extends ServiceImpl<ZGRevenueRecordMapper, ZGRevenueRecord> implements IZGRevenueRecordService {
}