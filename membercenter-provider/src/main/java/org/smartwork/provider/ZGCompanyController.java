package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGCmAttachService;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.enums.CompanyTeamStateEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.CompanyPageDto;
import org.smartwork.comm.model.TeamPageDto;
import org.smartwork.dal.entity.ZGCmAttach;
import org.smartwork.dal.entity.ZGCompany;
import org.smartwork.dal.entity.ZGTeam;
import org.smartwork.dal.entity.ZGTeamAttach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags={"公司管理"})
@RestController
@RequestMapping("/zgcompany")
public class ZGCompanyController extends BaseProvider<IZGCompanyService, ZGCompany> {

    @Autowired
    IZGCompanyService companyService;

    @Autowired
    IZGCmAttachService izgCmAttachService;

    /***
     * auditCompany方法概述:后台公司审核
     * @param id state
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGCompany>
     * @创建人 Tom
     * @创建时间 2020/3/31 18:14
     * @修改人 (修改了该文件请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/audit-company", method = RequestMethod.PUT)
    @ApiOperation("后台公司审核")
    public Result<ZGCompany> auditCompany(@RequestParam(value="id")Long id, @RequestParam(value = "state")String state) {
        Result<ZGCompany> result = new Result<ZGCompany>();
        ZGCompany zgCompany = companyService.getById(id);
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
        boolean isState = CompanyTeamStateEnum.existsCode(state);
        if(!isState){
            result.setBizCode(MemberBizResultEnum.NOT_CHECK_STATE.getBizCode());
            result.setMessage(String.format(MemberBizResultEnum.NOT_CHECK_STATE.getBizFormateMessage(), state));
            return result;
        }
        zgCompany.setState(state);
        companyService.updateById(zgCompany);
        result.setResult(zgCompany);
        return result;
    }

    /***
     * getAllCompany方法概述:查询所有公司
     * @param
     * @return org.forbes.comm.vo.Result<java.util.List < org.smartwork.dal.entity.ZGCompany>>
     * @创建人 Tom
     * @创建时间 2020/4/1 9:45
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/get-company", method = RequestMethod.GET)
    @ApiOperation("查询所有公司列表")
    public Result<IPage<ZGCompany>> getAllCompany(BasePageDto basePageDto, CompanyPageDto companyPageDto){
        Result<IPage<ZGCompany>> result=new Result<IPage<ZGCompany>>();
        QueryWrapper<ZGCompany> qw=new QueryWrapper<>();
        if(ConvertUtils.isNotEmpty(companyPageDto)){
            if(ConvertUtils.isNotEmpty(companyPageDto.getLegalPerson())){
                qw.like("legal_person",companyPageDto.getLegalPerson());
            }
        }
        IPage<ZGCompany> page=new Page<>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ZGCompany> zgCompanyIPage=companyService.page(page,qw);
        zgCompanyIPage.getRecords().stream().forEach(zgCompany -> {
            List<ZGCmAttach> zgCmAttaches = izgCmAttachService.list(new QueryWrapper<ZGCmAttach>().eq("data_id",zgCompany.getId()));
            zgCompany.setZgCmAttaches(zgCmAttaches);
        });
        result.setResult(zgCompanyIPage);
        return result;
    }


}