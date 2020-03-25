package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmPostService;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.comm.constant.CmRelUserCommonConstant;
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
 * @author nhy
 * @date 2020/3/16 16:00
 */
@RestController
@RequestMapping("/${smartwork.verision}/post")
@Api(tags = {"Api--公司中的岗位设置"})
@Slf4j
public class ZGCmPostApiProvider {

    @Autowired
    IZGCmPostService cmPostService;

    @Autowired
    IZGCmRelUserService cmRelUserService;

    /***
     * selectPost方法概述:查询公司岗位列表
     * @param cmPostDto
     * @创建人 nhy
     * @创建时间 2020/3/17 10:08
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-posts", method = RequestMethod.GET)
    @ApiOperation("查询岗位列表")
    public Result<IPage<ZGCmPost>> selectPost(BasePageDto basePageDto, ZGCmPostDto cmPostDto) {
        Result<IPage<ZGCmPost>> result = new Result<>();
        QueryWrapper<ZGCmPost> qw = new QueryWrapper<>();
        qw.eq(CompanyPostConstant.CM_ID, cmPostDto.getCmId());
        IPage<ZGCmPost> page = new Page<>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ZGCmPost> cmPosts = cmPostService.page(page, qw);
        result.setResult(cmPosts);
        return result;
    }

    /***
     * addPost方法概述:
     * @param zgCmPostDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmPostDto>
     * @创建人 nhy
     * @创建时间 2020/3/16 16:03
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/insert-post", method = RequestMethod.POST)
    @ApiOperation("新建岗位")
    public Result<ZGCmPostDto> addPost(@RequestBody @Validated(value = SaveValid.class) ZGCmPostDto zgCmPostDto) {
        Result<ZGCmPostDto> result = new Result<ZGCmPostDto>();
        int count = cmPostService.count(new QueryWrapper<ZGCmPost>().eq(CompanyPostConstant.POST_NAME, zgCmPostDto.getName()));
        if (count > 0) {
            result.setBizCode(MemberBizResultEnum.COMPANY_POST_EXEITS.getBizCode());
            result.setMessage(MemberBizResultEnum.COMPANY_POST_EXEITS.getBizMessage());
            return result;
        }
        cmPostService.addPost(zgCmPostDto);
        result.setResult(zgCmPostDto);
        return result;
    }

    /***
     * updatePost方法概述:修改岗位
     * @param zgCmPostDto
     * @创建人 nhy
     * @创建时间 2020/3/16 18:08
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/alter-post", method = RequestMethod.PUT)
    @ApiOperation("修改岗位")
    public Result<ZGCmPostDto> updatePost(@RequestBody @Validated(value = UpdateValid.class) ZGCmPostDto zgCmPostDto) {
        Result<ZGCmPostDto> result = new Result<>();
        int count = cmPostService.count(new QueryWrapper<ZGCmPost>().eq(CompanyPostConstant.POST_NAME, zgCmPostDto.getName()));
        if (count > 0) {
            result.setBizCode(MemberBizResultEnum.COMPANY_POST_EXEITS.getBizCode());
            result.setMessage(MemberBizResultEnum.COMPANY_POST_EXEITS.getBizMessage());
            return result;
        }
        cmPostService.updatePost(zgCmPostDto);
        result.setResult(zgCmPostDto);
        return result;
    }

    /***
     * deletePost方法概述:岗位删除
     * @param id
     * @return org.forbes.comm.vo.Result<java.lang.Boolean>
     * @创建人 nhy
     * @创建时间 2020/3/18 17:16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ApiImplicitParam(value = "公司岗位主键ID")
    @ApiOperation("岗位删除")
    public Result<Boolean> deletePost(@RequestParam(name = "id") Long id) {
        Result<Boolean> result = new Result<>();
        Integer count = cmRelUserService.count(new QueryWrapper<ZGCmRelUser>().eq(CmRelUserCommonConstant.POST_ID, id));
        if (count > 0) {
            result.setBizCode(MemberBizResultEnum.NO_DELECT_POST.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_DELECT_POST.getBizMessage());
            return result;
        }
        Boolean deletePost = cmPostService.removeById(id);
        result.setResult(deletePost);
        return result;
    }

    /***
     * deleteBatch方法概述:批量删除岗位
     * @param ids
     * @return org.forbes.comm.vo.Result<java.lang.Boolean>
     * @创建人 Tom
     * @创建时间 2020/3/23 13:45
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("批量删除岗位")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "岗位IDs", required = true)
    })
    @RequestMapping(value = "/delete-batch", method = RequestMethod.DELETE)
    public Result<Boolean> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<Boolean> result = new Result<Boolean>();
        try {
            cmPostService.removeByIds(Arrays.asList(ids.split(CommonConstant.SEPARATOR)));
        } catch (ForbesException e) {
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }

}
