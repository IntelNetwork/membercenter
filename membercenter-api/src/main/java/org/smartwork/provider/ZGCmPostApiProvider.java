package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmPostService;
import org.smartwork.comm.constant.CompanyConstant;
import org.smartwork.comm.constant.SaveValid;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.model.ZGCmPostDto;
import org.smartwork.dal.entity.ZGCmPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lzw
 * @date 2020/3/16 16:00
 */
@RestController
@RequestMapping("/${smartwork.verision}/post")
@Api(tags = {"Api--设置岗位管理"})
@Slf4j
public class ZGCmPostApiProvider {

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
    @RequestMapping(value = "/insert-post", method = RequestMethod.POST)
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
    @RequestMapping(value = "/alter-post", method = RequestMethod.PUT)
    @ApiOperation("修改岗位")
    public Result<ZGCmPostDto> updatePost(@RequestBody @Validated(value = UpdateValid.class) ZGCmPostDto zgCmPostDto) {
        Result<ZGCmPostDto> result=new Result<ZGCmPostDto>();
        zgCmPostService.updatePost(zgCmPostDto);
        result.setResult(zgCmPostDto);
        return result;
    }

    /***
     * selectPost方法概述:查询岗位列表
     * @param cmId
     * @return org.forbes.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGCmPost>>
     * @创建人 Tom
     * @创建时间 2020/3/17 10:08
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-post", method = RequestMethod.GET)
    @ApiOperation("查询岗位列表")
    public Result<List<ZGCmPost>> selectPost(@RequestParam(value = "cmId") Long cmId){
        Result<List<ZGCmPost>> result=new Result<List<ZGCmPost>>();
        List<ZGCmPost> zgCmPosts=zgCmPostService.list(new QueryWrapper<ZGCmPost>().eq(CompanyConstant.CMID,cmId));
        result.setResult(zgCmPosts);
        return result;
    }



}
