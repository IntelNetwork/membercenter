package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.UserStausEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.comm.constant.CmRelUserCommonConstant;
import org.smartwork.comm.constant.TeamRelUserCommonConstant;
import org.smartwork.comm.enums.AdminFlagEnum;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.comm.model.ZGCmRelUserPageDto;
import org.smartwork.comm.model.ZGTeamRelUserDto;
import org.smartwork.comm.model.ZGTeamRelUserPageDto;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.entity.ZGTeamRelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result<ZGCmRelUserDto> addCmUser(@RequestBody @Validated(value = SaveValid.class)ZGCmRelUserDto zgCmRelUserDto){
        Result<ZGCmRelUserDto> result=new Result<ZGCmRelUserDto>();
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
    public Result<ZGCmRelUserDto> updateCmUser(@RequestBody @Validated(value = UpdateValid.class)ZGCmRelUserDto zgCmRelUserDto){
        Result<ZGCmRelUserDto> result=new Result<ZGCmRelUserDto>();
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
    @RequestMapping(value = "/update-adminFlag", method = RequestMethod.PUT)
    @ApiOperation("设置管理员")
    public Result<ZGCmRelUser> updateAdminFlag(@RequestParam(value="cmId",required=true)Long cmId,
                                               @RequestParam(value="userId",required=true)Long userId,
                                               @RequestParam(value="adminFlag",required=true)String adminFlag){
        Result<ZGCmRelUser> result=new Result<ZGCmRelUser>();
        boolean adminFlags = AdminFlagEnum.existsCode(adminFlag);
        if(!adminFlags){
            result.setBizCode(MemberBizResultEnum.CM_ADMINFLAG_NO_EXISTS.getBizCode());
            result.setMessage(String.format(MemberBizResultEnum.CM_ADMINFLAG_NO_EXISTS.getBizFormateMessage(), adminFlag));
            return result;
        }
        QueryWrapper<ZGCmRelUser> qw = new QueryWrapper<>();
        qw.eq(CmRelUserCommonConstant.CM_ID, cmId);
        qw.eq(CmRelUserCommonConstant.CM_USER_ID, userId);
        ZGCmRelUser zgCmRelUser = zgCmRelUserService.getOne(qw);
        if(ConvertUtils.isEmpty(zgCmRelUser)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        zgCmRelUser.setAdminFlag(adminFlag);
        zgCmRelUserService.updateById(zgCmRelUser);
        return result;

    }
}
