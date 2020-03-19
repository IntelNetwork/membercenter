package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmPostService;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.comm.constant.CompanyConstant;
import org.smartwork.comm.constant.CompanyPostConstant;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCmPostDto;
import org.smartwork.dal.entity.ZGCmPost;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    @Autowired
    IZGCmRelUserService zgCmRelUserService;

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
     * deletePost方法概述:岗位删除
     * @param id
     * @return org.forbes.comm.vo.Result<java.lang.Boolean>
     * @创建人 Tom
     * @创建时间 2020/3/18 17:16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ApiOperation("岗位删除")
    public Result<Boolean> deletePost(@RequestParam(name="id",required=true) Long id) {
        Result<Boolean> result = new Result<Boolean>();
        Integer count = zgCmRelUserService.count(new QueryWrapper<ZGCmRelUser>().eq(CompanyPostConstant.POSTID,id));
        if(count > 0){
            result.setBizCode(MemberBizResultEnum.NO_DELECT_POST.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_DELECT_POST.getBizMessage());
            return result;
        }
        Boolean deletePost=zgCmPostService.removeById(id);
        result.setResult(deletePost);
        return result;
    }

    @ApiOperation("批量删除岗位")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids",value = "岗位IDs",required = true)
    })
    @RequestMapping(value = "/delete-batch", method = RequestMethod.DELETE)
    public Result<Boolean> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        Result<Boolean> result = new Result<Boolean>();
        try {
            zgCmPostService.removeByIds(Arrays.asList(ids.split(CommonConstant.SEPARATOR)));
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }

}
