package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
