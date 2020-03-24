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
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.comm.constant.CmRelUserCommonConstant;
import org.smartwork.comm.enums.CmAdminFlagEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.comm.model.ZGCmRelUserPageDto;
import org.smartwork.comm.vo.ZGCmRelUserVo;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzw
 * @date 2020/3/17 10:24
 */
@RestController
@RequestMapping("/${smartwork.verision}/company-user")
@Api(tags = {"Api--添加员工和设置员工岗位"})
@Slf4j
public class ZGCmRelUserApiProvider {

    @Autowired
    IZGCmRelUserService zgCmRelUserService;

    /***
     * page方法概述:分页查询公司人员列表
     * @param basePageDto, pageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.smartwork.dal.entity.ZGCmRelUser>>
     * @创建人 Tom
     * @创建时间 2020/3/23 12:56
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/company-users", method = RequestMethod.GET)
    @ApiOperation("分页查询公司人员列表")
    public Result<IPage<ZGCmRelUser>> page(BasePageDto basePageDto, ZGCmRelUserPageDto pageDto) {
        Result<IPage<ZGCmRelUser>> result = new Result<IPage<ZGCmRelUser>>();
        QueryWrapper<ZGCmRelUser> qw = new QueryWrapper<>();
        if (ConvertUtils.isNotEmpty(pageDto)) {
            if (ConvertUtils.isNotEmpty(pageDto.getUserName())) {
                qw.like(CmRelUserCommonConstant.CM_USER_NAME, pageDto.getUserName());
            }
        }
        qw.eq(CmRelUserCommonConstant.CM_ID, pageDto.getCmId());
        IPage<ZGCmRelUser> page = new Page<ZGCmRelUser>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ZGCmRelUser> pages = zgCmRelUserService.page(page, qw);
        result.setResult(pages);
        return result;
    }

    /***
     * addCmUser方法概述:添加员工
     * @param zgCmRelUserDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmRelUserDto>
     * @创建人 Tom
     * @创建时间 2020/3/17 11:50
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/insert-cm-user", method = RequestMethod.POST)
    @ApiOperation("添加员工")
    public Result<ZGCmRelUserDto> addCmUser(@RequestBody @Validated(value = SaveValid.class) ZGCmRelUserDto zgCmRelUserDto) {
        Result<ZGCmRelUserDto> result = new Result<ZGCmRelUserDto>();
        //对比当前操作人是否是管理员
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser=zgCmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq(CmRelUserCommonConstant.CM_USER_ID,user.getId()));
        if(!zgCmRelUser.getAdminFlag().equals(1)){
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_ADD_USER.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_ADD_USER.getBizMessage());
            return result;
        }
        zgCmRelUserService.addCmUser(zgCmRelUserDto);
        result.setResult(zgCmRelUserDto);
        return result;
    }

    /***
     * updateCmUser方法概述:员工岗位变更
     * @param zgCmRelUserDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmRelUserDto>
     * @创建人 Tom
     * @创建时间 2020/3/17 13:59
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/alter-post-user", method = RequestMethod.PUT)
    @ApiOperation("员工岗位变更")
    public Result<ZGCmRelUserDto> updateCmUser(@RequestBody @Validated(value = UpdateValid.class) ZGCmRelUserDto zgCmRelUserDto) {
        Result<ZGCmRelUserDto> result = new Result<ZGCmRelUserDto>();
        //对比当前操作人是否是管理员
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser=zgCmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq(CmRelUserCommonConstant.CM_USER_ID,user.getId()));
        if(!zgCmRelUser.getAdminFlag().equals(1)){
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_UPDATE_CM.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_UPDATE_CM.getBizMessage());
            return result;
        }
        zgCmRelUserService.updateCmUser(zgCmRelUserDto);
        result.setResult(zgCmRelUserDto);
        return result;
    }

    /***
     * removeTeamUser方法概述:公司人员离职
     * @param cmId, userId
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmRelUserDto>
     * @创建人 Tom
     * @创建时间 2020/3/23 12:50
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/company-user-remove", method = RequestMethod.DELETE)
    @ApiOperation("公司人员离职")
    public Result<ZGCmRelUserDto> removeTeamUser(@RequestParam(value = "cmId") Long cmId, @RequestParam(value = "userId") Long userId) {
        Result<ZGCmRelUserDto> result = new Result<ZGCmRelUserDto>();
        if (ConvertUtils.isEmpty(cmId) || ConvertUtils.isEmpty(userId)) {
            result.setBizCode(MemberBizResultEnum.EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        QueryWrapper<ZGCmRelUser> qw = new QueryWrapper<>();
        qw.eq(CmRelUserCommonConstant.CM_ID, cmId);
        qw.eq(CmRelUserCommonConstant.CM_USER_ID, userId);
        ZGCmRelUser zgCmRelUser = zgCmRelUserService.getOne(qw);
        zgCmRelUserService.removeById(zgCmRelUser);
        return result;
    }

    /***
     * updateAdminFlag方法概述:公司设置管理员
     * @param cmId, userId, adminFlag
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGCmRelUser>
     * @创建人 Tom
     * @创建时间 2020/3/23 13:32
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-adminflag", method = RequestMethod.PUT)
    @ApiOperation("设置管理员")
    public Result<ZGCmRelUser> updateAdminFlag(@RequestParam(value = "cmId", required = true) Long cmId,
                                               @RequestParam(value = "userId", required = true) Long userId,
                                               @RequestParam(value = "adminFlag", required = true) String adminFlag) {
        Result<ZGCmRelUser> result = new Result<ZGCmRelUser>();
        boolean adminFlags = CmAdminFlagEnum.existsCode(adminFlag);
        if (!adminFlags) {
            result.setBizCode(MemberBizResultEnum.CM_ADMINFLAG_NO_EXISTS.getBizCode());
            result.setMessage(String.format(MemberBizResultEnum.CM_ADMINFLAG_NO_EXISTS.getBizFormateMessage(), adminFlag));
            return result;
        }
        QueryWrapper<ZGCmRelUser> qw = new QueryWrapper<>();
        qw.eq(CmRelUserCommonConstant.CM_ID, cmId);
        qw.eq(CmRelUserCommonConstant.CM_USER_ID, userId);
        ZGCmRelUser zgCmRelUser = zgCmRelUserService.getOne(qw);
        if (ConvertUtils.isEmpty(zgCmRelUser)) {
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        zgCmRelUser.setAdminFlag(adminFlag);
        zgCmRelUserService.updateById(zgCmRelUser);
        return result;

    }


    /***
     * 方法概述:员工详情
     * @param cmId,userId
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/team-user-detail", method = RequestMethod.GET)
    @ApiOperation("公司查看员工详情")
    public Result<ZGCmRelUserVo> cmUserDetail(@RequestParam(value = "cmId") Long cmId, @RequestParam(value = "userName") String userName) {
        Result<ZGCmRelUserVo> result = new Result<>();
        if (ConvertUtils.isEmpty(cmId) || ConvertUtils.isEmpty(userName)) {
            result.setBizCode(MemberBizResultEnum.EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        ZGCmRelUserVo teamRelUserVo = zgCmRelUserService.cmUserDetail(cmId, userName);
        result.setResult(teamRelUserVo);
        return result;
    }

    /***
     * selectAdminFlag方法概述:判断当前登录用户是否是管理员
     * @param
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGCmRelUser>
     * @创建人 Tom
     * @创建时间 2020/3/24 10:20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-adminflag", method = RequestMethod.GET)
    @ApiOperation("判断当前登录用户是否是管理员")
    public Result<ZGCmRelUser> selectAdminFlag(){
        Result<ZGCmRelUser> result=new Result<ZGCmRelUser>();
        //对比当前操作人是否是管理员
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser=zgCmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq(CmRelUserCommonConstant.CM_USER_ID,user.getId()));
        result.setResult(zgCmRelUser);
        return result;
    }

}
