package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.ResultEnum;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.enums.ReviewStatusEnum;
import org.smartwork.dal.entity.ZGCompany;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags={"公司管理"})
@RestController
@RequestMapping("/zgcompany")
public class ZGCompanyController extends BaseProvider<IZGCompanyService, ZGCompany> {

}