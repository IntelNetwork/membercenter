package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.ResultEnum;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.enums.CompanyTeamStateEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.enums.ReviewStatusEnum;
import org.smartwork.comm.model.AuditCompanyDto;
import org.smartwork.dal.entity.ZGCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(tags={"公司管理"})
@RestController
@RequestMapping("/zgcompany")
public class ZGCompanyController extends BaseProvider<IZGCompanyService, ZGCompany> {

    @Autowired
    IZGCompanyService companyService;

    /***
     * auditCompany方法概述:后台公司审核
     * @param auditCompanyDto
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGCompany>
     * @创建人 Tom
     * @创建时间 2020/3/31 18:14
     * @修改人 (修改了该文件请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/audit-company", method = RequestMethod.PUT)
    @ApiOperation("后台公司审核")
    public Result<ZGCompany> auditCompany(@RequestBody AuditCompanyDto auditCompanyDto) {
        Result<ZGCompany> result = new Result<ZGCompany>();
        ZGCompany zgCompany = companyService.getById(auditCompanyDto.getId());
        if (ConvertUtils.isEmpty(zgCompany)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //必须为待审核才可以审核
        if(!zgCompany.getState().equalsIgnoreCase(CompanyTeamStateEnum.AUDITED.getCode())){
            result.setBizCode(MemberBizResultEnum.COMPANY_NOT_CHECK_STATE.getBizCode());
            result.setMessage(MemberBizResultEnum.COMPANY_NOT_CHECK_STATE.getBizMessage());
            return result;
        }
        if (auditCompanyDto.getState().equalsIgnoreCase(CompanyTeamStateEnum.AUDITED_UN_PASS.getCode())) {
            zgCompany.setState(CompanyTeamStateEnum.AUDITED_UN_PASS.getCode());
        } else {
            zgCompany.setState(CompanyTeamStateEnum.AUDITED_PASS.getCode());
        }
        companyService.updateById(zgCompany);
        result.setResult(zgCompany);
        return result;
    }
}