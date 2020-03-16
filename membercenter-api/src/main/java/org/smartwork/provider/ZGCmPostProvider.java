package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmPostService;
import org.smartwork.comm.constant.SaveValid;
import org.smartwork.comm.model.ZGCmPostDto;
import org.smartwork.comm.model.ZGCompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzw
 * @date 2020/3/16 16:00
 */
@RestController
@RequestMapping("/${smartwork.verision}/post")
@Api(tags = {"Api--设置岗位管理"})
@Slf4j
public class ZGCmPostProvider {

    @Autowired
    IZGCmPostService zgCmPostService;

    /***
     * addPost方法概述:
     * @param zgCmPostDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmPostDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 16:03
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("新建岗位")
    public Result<ZGCmPostDto> addPost(@RequestBody @Validated(value = SaveValid.class) ZGCmPostDto zgCmPostDto) {
        Result<ZGCmPostDto> result=new Result<ZGCmPostDto>();
        zgCmPostService.addPost(zgCmPostDto);
        result.setResult(zgCmPostDto);
        return result;
    }

    /***
     * updatePost方法概述:修改岗位
     * @param zgCmPostDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmPostDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 18:08
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("修改岗位")
    public Result<ZGCmPostDto> updatePost(@RequestBody @Validated(value = SaveValid.class) ZGCmPostDto zgCmPostDto) {
        Result<ZGCmPostDto> result=new Result<ZGCmPostDto>();
        zgCmPostService.updatePost(zgCmPostDto);
        result.setResult(zgCmPostDto);
        return result;
    }


}
