package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.YesNoEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.enums.CmAdminFlagEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.comm.model.ZGCmRelUserPageDto;
import org.smartwork.comm.vo.ZGCmRelUserVo;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.entity.ZGCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author nhy
 * @date 2020/3/17 10:24
 */
@RestController
@RequestMapping("/${smartwork.verision}/company-user")
@Api(tags = {"Api--公司成员列表,岗位管理等"})
@Slf4j
public class ZGCmRelUserApiProvider {

    @Autowired
    IZGCmRelUserService cmRelUserService;
    @Autowired
    IZGCompanyService companyService;


    /***
     * page方法概述:分页查询公司人员列表
     * @param basePageDto, pageDto
     * @创建人 nhy
     * @创建时间 2020/3/23 12:56
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ApiOperation("分页查询公司人员列表")
    public Result<IPage<ZGCmRelUser>> page(BasePageDto basePageDto, ZGCmRelUserPageDto pageDto) {
        Result<IPage<ZGCmRelUser>> result = new Result<>();
        QueryWrapper<ZGCmRelUser> qw = new QueryWrapper<>();
        qw.eq("cm_id", pageDto.getCmId());
        if (ConvertUtils.isNotEmpty(pageDto)) {
            if (ConvertUtils.isNotEmpty(pageDto.getUserName())) {
                qw.like("user_name", pageDto.getUserName());
            }
        }
        IPage<ZGCmRelUser> page = new Page<>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ZGCmRelUser> pages = cmRelUserService.page(page, qw);
        result.setResult(pages);
        return result;
    }

    /***
     * addCmUser方法概述:新增公司成员,岗位等
     * @param zgCmRelUserDto
     * @创建人 nhy
     * @创建时间 2020/3/17 11:50
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation("添加员工")
    public Result<ZGCmRelUserDto> addCmUser(@RequestBody @Validated(value = SaveValid.class) ZGCmRelUserDto zgCmRelUserDto) {
        Result<ZGCmRelUserDto> result = new Result<ZGCmRelUserDto>();
        //对比当前操作人是否是管理员
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser = cmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq("user_id", user.getId()));
        if (!zgCmRelUser.getAdminFlag().equals(CmAdminFlagEnum.ORDINARY.getCode())) {
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_ADD_USER.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_ADD_USER.getBizMessage());
            return result;
        }
        cmRelUserService.addCmUser(zgCmRelUserDto);
        result.setResult(zgCmRelUserDto);
        return result;
    }

    /***
     * updateCmUser方法概述:员工岗位变更
     * @param zgCmRelUserDto
     * @创建人 nhy
     * @创建时间 2020/3/17 13:59
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/modify", method = RequestMethod.PUT)
    @ApiOperation("员工岗位变更")
    public Result<ZGCmRelUserDto> updateCmUser(@RequestBody @Validated(value = UpdateValid.class) ZGCmRelUserDto zgCmRelUserDto) {
        Result<ZGCmRelUserDto> result = new Result<ZGCmRelUserDto>();
        //对比当前操作人是否是管理员
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser = cmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq("user_id", user.getId()));
        if (!zgCmRelUser.getAdminFlag().equals(CmAdminFlagEnum.ORDINARY.getCode())) {
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_UPDATE_CM.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_UPDATE_CM.getBizMessage());
            return result;
        }
        cmRelUserService.updateCmUser(zgCmRelUserDto);
        result.setResult(zgCmRelUserDto);
        return result;
    }

    /***
     * removeTeamUser方法概述:删除公司人员
     * @param cmId, userId
     * @创建人 nhy
     * @创建时间 2020/3/23 12:50
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ApiOperation("删除公司人员")
    public Result<ZGCmRelUserDto> removeTeamUser(@RequestParam(value = "cmId") Long cmId, @RequestParam(value = "userId") Long userId) {
        Result<ZGCmRelUserDto> result = new Result<>();
        if (ConvertUtils.isEmpty(cmId) || ConvertUtils.isEmpty(userId)) {
            result.setBizCode(MemberBizResultEnum.EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        //对比当前操作人是否是管理员
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser relUser = cmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq("user_id", user.getId()));
        if (!relUser.getAdminFlag().equals(CmAdminFlagEnum.ORDINARY.getCode())) {
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_UPDATE_CM.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_UPDATE_CM.getBizMessage());
            return result;
        }
        QueryWrapper<ZGCmRelUser> qw = new QueryWrapper<>();
        qw.eq("cm_id", cmId);
        qw.eq("user_id", userId);
        ZGCmRelUser cmRelUser = cmRelUserService.getOne(qw);
        cmRelUserService.removeById(cmRelUser);
        return result;
    }

    /***
     * updateAdminFlag方法概述:公司设置管理员
     * @param cmId, userId, adminFlag
     * @创建人 nhy
     * @创建时间 2020/3/23 13:32
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/admin-flag", method = RequestMethod.PUT)
    @ApiOperation("負責人设置管理员")
    public Result<ZGCmRelUser> updateAdminFlag(@RequestParam(value = "cmId", required = true) Long cmId,
                                               @RequestParam(value = "userId", required = true) Long userId,
                                               @RequestParam(value = "adminFlag", required = true) Integer adminFlag) {
        Result<ZGCmRelUser> result = new Result<>();
        boolean adminFlags = CmAdminFlagEnum.existsCode(adminFlag);
        if (!adminFlags) {
            result.setBizCode(MemberBizResultEnum.CM_ADMINFLAG_NO_EXISTS.getBizCode());
            result.setMessage(String.format(MemberBizResultEnum.CM_ADMINFLAG_NO_EXISTS.getBizFormateMessage(), adminFlag));
            return result;
        }
        //判断当前登录人是否为公司负责人,只有负责人才可以设置管理员
        SysUser user = UserContext.getSysUser();
        ZGCompany com = companyService.getOne(new QueryWrapper<ZGCompany>().eq("legal_person", user.getRealname()));
        if (!com.getLegalPerson().equalsIgnoreCase(user.getRealname())) {
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_MODIFY.getBizMessage());
            return result;
        }
        QueryWrapper<ZGCmRelUser> qw = new QueryWrapper<>();
        qw.eq("cm_id", cmId);
        qw.eq("user_id", userId);
        ZGCmRelUser zgCmRelUser = cmRelUserService.getOne(qw);
        if (ConvertUtils.isEmpty(zgCmRelUser)) {
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        zgCmRelUser.setAdminFlag(adminFlag);
        cmRelUserService.updateById(zgCmRelUser);
        return result;

    }


    /***
     * 方法概述:员工详情
     * @param cmId,userId
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/20
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/cm-user-details", method = RequestMethod.GET)
    @ApiOperation("公司查看员工详情")
    public Result<ZGCmRelUserVo> cmUserDetail(@RequestParam(value = "cmId") Long cmId, @RequestParam(value = "userName") String userName) {
        Result<ZGCmRelUserVo> result = new Result<>();
        if (ConvertUtils.isEmpty(cmId) || ConvertUtils.isEmpty(userName)) {
            result.setBizCode(MemberBizResultEnum.EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        ZGCmRelUserVo teamRelUserVo = cmRelUserService.cmUserDetail(cmId, userName);
        result.setResult(teamRelUserVo);
        return result;
    }

    /***
     * selectAdminFlag方法概述:判断当前登录用户是否是管理员
     * @创建人 nhy
     * @创建时间 2020/3/24 10:20
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/if-adminflag", method = RequestMethod.GET)
    @ApiOperation("判断当前登录用户是否是管理员")
    public Result<String> selectAdminFlag() {
        Result<String> result = new Result<>();
        //对比当前操作人是否是管理员
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser = cmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq("user_id", user.getId()));
        if (zgCmRelUser.getAdminFlag() == Integer.valueOf(CmAdminFlagEnum.ORDINARY.getCode())) {
            result.setResult(YesNoEnum.YES.getCode());
            result.setMessage(MemberBizResultEnum.USER_ADMIN.getBizMessage());
        } else {
            result.setResult(YesNoEnum.NO.getCode());
            result.setMessage(MemberBizResultEnum.USER_UN_ADMIN.getBizMessage());
        }
        return result;
    }

}
