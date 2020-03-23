package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGWorkPlanService;
import org.smartwork.dal.entity.ZGWorkPlan;
import org.smartwork.dal.mapper.ZGWorkPlanMapper;
import org.smartwork.dal.mapper.ext.ZGWorkPlanExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZGWorkPlanServiceImpl extends ServiceImpl<ZGWorkPlanMapper, ZGWorkPlan> implements IZGWorkPlanService {

    @Autowired
    ZGWorkPlanExtMapper zgWorkPlanExtMapper;

    /***
     * selectPlanDay方法概述:查询我的日程计划
     * @param startTime, endTime
     * @return org.forbes.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGWorkPlan>>
     * @创建人 Tom
     * @创建时间 2020/3/23 12:34
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public List<ZGWorkPlan> selectPlanDay(Date startTime, Date endTime) {
        return zgWorkPlanExtMapper.selectPlanDay(startTime,endTime);
    }
}