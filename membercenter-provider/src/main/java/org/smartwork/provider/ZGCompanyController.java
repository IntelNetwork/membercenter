package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.dal.entity.ZGCompany;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"公司管理"})
@RestController
@RequestMapping("/zgcompany")
public class ZGCompanyController extends BaseProvider<IZGCompanyService, ZGCompany> {
}