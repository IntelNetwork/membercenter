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
import org.smartwork.biz.service.IZGLevelPermMemberService;
import org.smartwork.comm.enums.CompanyTeamStateEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCompanyDto;
import org.smartwork.comm.model.ZGLevelPermMemberDto;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.entity.ZGCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzw
 * @date 2020/3/31 9:05
 */
@RestController
@RequestMapping("/${smartwork.verision}/permmember")
@Api(tags = {"Api--分配成员权限"})
@Slf4j
public class ZGLevelPermMemberProvider {

    @Autowired
    IZGLevelPermMemberService izgLevelPermMemberService;

    @Autowired
    IZGCmRelUserService cmRelUserService;

    @Autowired
    IZGCompanyService companyService;

    /***
     * addPermMember方法概述:分配会员权限
     * @param zgLevelPermMemberDtos
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGLevelPermMemberDto>
     * @创建人 Tom
     * @创建时间 2020/3/31 9:25
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/insert-perm-member", method = RequestMethod.POST)
    @ApiOperation("分配会员权限")
    public Result<Boolean> addPermMember(@RequestParam(name = "memberId") Long memberId, @RequestBody @Validated(value = SaveValid.class) List<ZGLevelPermMemberDto> zgLevelPermMemberDtos) {
        Result<Boolean> result = new Result<Boolean>();
        if (ConvertUtils.isEmpty(zgLevelPermMemberDtos)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser = cmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq("user_id", user.getId()));
        ZGCompany company = companyService.getById(zgCmRelUser.getCmId());
        if (!company.getLegalPerson().equals(user.getRealname())) {
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_CM.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_CM.getBizMessage());
            return result;
        }
        izgLevelPermMemberService.addPermMember(memberId,zgLevelPermMemberDtos);
        result.setResult(true);
        return result;
    }


}
