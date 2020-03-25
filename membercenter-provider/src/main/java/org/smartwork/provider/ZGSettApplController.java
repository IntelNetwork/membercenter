package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.dal.entity.ZGSettAppl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"结算申请管理"})
@RestController
@RequestMapping("/zgsettappl")
public class ZGSettApplController extends BaseProvider<IZGSettApplService, ZGSettAppl> {
}