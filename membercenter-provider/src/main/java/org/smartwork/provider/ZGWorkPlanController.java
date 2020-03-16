package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGWorkPlanService;
import org.smartwork.dal.entity.ZGWorkPlan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"工作计划管理"})
@RestController
@RequestMapping("/zgworkplan")
public class ZGWorkPlanController extends BaseProvider<IZGWorkPlanService, ZGWorkPlan> {
}