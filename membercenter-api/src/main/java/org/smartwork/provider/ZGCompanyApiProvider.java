package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.constant.CmRelUserCommonConstant;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCompanyDto;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * 类概述:Api新建公司，修改，设置岗位管理
 * @创建人 Tom
 * @创建时间 2020/3/16
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/company")
@Api(tags = {"Api--创建公司-修改公司管理"})
@Slf4j
public class ZGCompanyApiProvider {

    @Autowired
    IZGCompanyService zgCompanyService;

    @Autowired
    IZGCmRelUserService zgCmRelUserService;

    /***
     * addCompany方法概述:创建公司
     * @param zgCompanyDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCompanyDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/insert-company", method = RequestMethod.POST)
    @ApiOperation("创建公司")
    public Result<ZGCompanyDto> addCompany(@RequestBody @Validated(value = SaveValid.class) ZGCompanyDto zgCompanyDto) {
        Result<ZGCompanyDto> result=new Result<ZGCompanyDto>();
        zgCompanyService.addCompany(zgCompanyDto);
        result.setResult(zgCompanyDto);
        return result;
    }

    /***
     * updateCompany方法概述:公司信息修改
     * @param zgCompanyDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCompanyDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/alter-company", method = RequestMethod.PUT)
    @ApiOperation("公司信息修改")
    public Result<ZGCompanyDto> updateCompany(@RequestBody @Validated(value = SaveValid.class) ZGCompanyDto zgCompanyDto) {
        Result<ZGCompanyDto> result=new Result<ZGCompanyDto>();
        //对比当前操作人是否是管理员
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser=zgCmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq(CmRelUserCommonConstant.CM_USER_ID,user.getId()));
        if(!zgCmRelUser.getAdminFlag().equals(1)){
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_CM.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_CM.getBizMessage());
            return result;
        }
        zgCompanyService.updateCompany(zgCompanyDto);
        result.setResult(zgCompanyDto);
        return result;
    }


}
