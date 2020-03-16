package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGProTaskService;
import org.smartwork.dal.entity.ZGProTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"项目-任务管理"})
@RestController
@RequestMapping("/zgprotask")
public class ZGProTaskController extends BaseProvider<IZGProTaskService, ZGProTask> {
}