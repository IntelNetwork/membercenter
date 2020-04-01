package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTeamAttachService;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.comm.enums.CompanyTeamStateEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.TeamPageDto;
import org.smartwork.dal.entity.ZGCmAttach;
import org.smartwork.dal.entity.ZGTeam;
import org.smartwork.dal.entity.ZGTeamAttach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={"团队管理"})
@RestController
@RequestMapping("/zgteam")
public class ZGTeamController extends BaseProvider<IZGTeamService, ZGTeam> {

    @Autowired
    IZGTeamService izgTeamService;

    @Autowired
    IZGTeamAttachService izgTeamAttachService;

    /***
     * auditTask方法概述:后台公司审核
     * @param id
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGCompany>
     * @创建人 Tom
     * @创建时间 2020/3/31 18:14
     * @修改人 (修改了该文件请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/audit-team", method = RequestMethod.PUT)
    @ApiOperation("后台公司审核")
    public Result<ZGTeam> auditTeam(@RequestParam(value="id")Long id, @RequestParam(value = "state")String state) {
        Result<ZGTeam> result = new Result<ZGTeam>();
        ZGTeam zgTeam = izgTeamService.getById(id);
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
        boolean isState = CompanyTeamStateEnum.existsCode(state);
        if(!isState){
            result.setBizCode(MemberBizResultEnum.NOT_CHECK_STATE.getBizCode());
            result.setMessage(String.format(MemberBizResultEnum.NOT_CHECK_STATE.getBizFormateMessage(), state));
            return result;
        }
        zgTeam.setState(state);
        izgTeamService.updateById(zgTeam);
        result.setResult(zgTeam);
        return result;
    }

    /***
     * getAllTeam方法概述:查询所有团队列表
     * @param
     * @return org.forbes.comm.vo.Result<java.util.List < org.smartwork.dal.entity.ZGTeam>>
     * @创建人 Tom
     * @创建时间 2020/4/1 10:33
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/get-team", method = RequestMethod.GET)
    @ApiOperation("查询所有团队列表")
    public Result<IPage<ZGTeam>> getAllTeam(BasePageDto basePageDto, TeamPageDto teamPageDto){
        Result<IPage<ZGTeam>> result=new Result<IPage<ZGTeam>>();
        QueryWrapper<ZGTeam> qw=new QueryWrapper<>();
        if(ConvertUtils.isNotEmpty(teamPageDto)){
            if(ConvertUtils.isNotEmpty(teamPageDto.getLegalPerson())){
                qw.like("legal_person",teamPageDto.getLegalPerson());
            }
        }
        IPage<ZGTeam> page=new Page<>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ZGTeam> zgTeamIPage=izgTeamService.page(page,qw);
        zgTeamIPage.getRecords().stream().forEach(zgTeam -> {
            List<ZGTeamAttach> zgTeams = izgTeamAttachService.list(new QueryWrapper<ZGTeamAttach>().eq("data_id",zgTeam.getId()));
            zgTeam.setZgTeamAttaches(zgTeams);
        });
        result.setResult(zgTeamIPage);
        return result;
    }

}