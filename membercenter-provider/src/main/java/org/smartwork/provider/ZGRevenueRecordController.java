package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGRevenueRecordService;
import org.smartwork.dal.entity.ZGRevenueRecord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"个人营收记录管理"})
@RestController
@RequestMapping("/zgrevenuerecord")
public class ZGRevenueRecordController extends BaseProvider<IZGRevenueRecordService, ZGRevenueRecord> {
}