package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGEarningsService;
import org.smartwork.dal.entity.ZGEarnings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"智工收益管理"})
@RestController
@RequestMapping("/zgearnings")
public class ZGEarningsController extends BaseProvider<IZGEarningsService, ZGEarnings> {
}