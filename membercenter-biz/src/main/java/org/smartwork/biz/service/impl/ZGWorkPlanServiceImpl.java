package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGWorkPlanService;
import org.smartwork.dal.entity.ZGWorkPlan;
import org.smartwork.dal.mapper.ZGWorkPlanMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGWorkPlanServiceImpl extends ServiceImpl<ZGWorkPlanMapper, ZGWorkPlan> implements IZGWorkPlanService {
}