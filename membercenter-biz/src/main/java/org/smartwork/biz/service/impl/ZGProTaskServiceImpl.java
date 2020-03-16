package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGProTaskService;
import org.smartwork.dal.entity.ZGProTask;
import org.smartwork.dal.mapper.ZGProTaskMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGProTaskServiceImpl extends ServiceImpl<ZGProTaskMapper, ZGProTask> implements IZGProTaskService {
}