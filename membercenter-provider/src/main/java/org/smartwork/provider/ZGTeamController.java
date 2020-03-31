package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.comm.enums.CompanyTeamStateEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.AuditCompanyDto;
import org.smartwork.comm.model.AuditTeamDto;
import org.smartwork.dal.entity.ZGCompany;
import org.smartwork.dal.entity.ZGTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"团队管理"})
@RestController
@RequestMapping("/zgteam")
public class ZGTeamController extends BaseProvider<IZGTeamService, ZGTeam> {

    @Autowired
    IZGTeamService izgTeamService;

    /***
     * auditTask方法概述:后台公司审核
     * @param auditTeamDto
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGCompany>
     * @创建人 Tom
     * @创建时间 2020/3/31 18:14
     * @修改人 (修改了该文件请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/audit-team", method = RequestMethod.PUT)
    @ApiOperation("后台公司审核")
    public Result<ZGTeam> auditTeam(@RequestBody AuditTeamDto auditTeamDto) {
        Result<ZGTeam> result = new Result<ZGTeam>();
        ZGTeam zgTeam = izgTeamService.getById(auditTeamDto.getId());
        if (ConvertUtils.isEmpty(zgTeam)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //必须为待审核才可以审核
        if(!zgTeam.getState().equalsIgnoreCase(CompanyTeamStateEnum.AUDITED.getCode())){
            result.setBizCode(MemberBizResultEnum.COMPANY_NOT_CHECK_STATE.getBizCode());
            result.setMessage(MemberBizResultEnum.COMPANY_NOT_CHECK_STATE.getBizMessage());
            return result;
        }
        if (auditTeamDto.getState().equalsIgnoreCase(CompanyTeamStateEnum.AUDITED_UN_PASS.getCode())) {
            zgTeam.setState(CompanyTeamStateEnum.AUDITED_UN_PASS.getCode());
        } else {
            zgTeam.setState(CompanyTeamStateEnum.AUDITED_PASS.getCode());
        }
        izgTeamService.updateById(zgTeam);
        result.setResult(zgTeam);
        return result;
    }
}