package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.enums.CompanyTeamStateEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCompanyDto;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.entity.ZGCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * 类概述:公司创建,完善信息等管理
 * @创建人 nhy
 * @创建时间 2020/3/16
 * @修改人 (修改了该文件 ， 请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/company")
@Api(tags = {"Api--公司创建,完善信息等管理"})
@Slf4j
public class ZGCompanyApiProvider {

    @Autowired
    IZGCompanyService companyService;

    @Autowired
    IZGCmRelUserService cmRelUserService;

    /***
     * addCompany方法概述:创建公司
     * @param zgCompanyDto
     * @创建人 nhy
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/insert-company", method = RequestMethod.POST)
    @ApiOperation("创建公司")
    public Result<ZGCompanyDto> addCompany(@RequestBody @Validated(value = SaveValid.class) ZGCompanyDto zgCompanyDto) {
        Result<ZGCompanyDto> result = new Result<>();
        if (ConvertUtils.isEmpty(zgCompanyDto)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //默认将当前登录人姓名那个设置为团队负责人
        zgCompanyDto.setLegalPerson(UserContext.getSysUser().getRealname());
        //默认审核状态
        zgCompanyDto.setState(CompanyTeamStateEnum.AUDITED.getCode());
        companyService.addCompany(zgCompanyDto);
        result.setResult(zgCompanyDto);
        return result;
    }

    /***
     * updateCompany方法概述:公司信息修改(完善公司信息)
     * @param zgCompanyDto
     * @创建人 nhy
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("公司信息修改(完善公司信息)")
    @RequestMapping(value = "/alter-company", method = RequestMethod.PUT)
    public Result<ZGCompanyDto> updateCompany(@RequestBody @Validated(value = UpdateValid.class) ZGCompanyDto zgCompanyDto) {
        Result<ZGCompanyDto> result = new Result<>();
        //对比当前操作人是否是公司负责人
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser = cmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq("user_id", user.getId()));
        ZGCompany company = companyService.getById(zgCmRelUser.getCmId());
        if (!company.getLegalPerson().equals(user.getRealname())) {
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_CM.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_CM.getBizMessage());
            return result;
        }
        companyService.updateCompany(zgCompanyDto);
        result.setResult(zgCompanyDto);
        return result;
    }


}
