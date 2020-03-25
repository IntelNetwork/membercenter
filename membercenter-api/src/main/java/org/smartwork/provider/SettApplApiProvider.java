package org.smartwork.provider;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.utils.QueryWrapperUtils;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.ResultEnum;
import org.smartwork.biz.service.IZGEarningsService;
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.comm.enums.ReviewStatusEnum;
import org.smartwork.comm.enums.SettlStatusEnum;
import org.smartwork.comm.enums.SettlTypeEnum;
import org.smartwork.dal.entity.ZGSettAppl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/***
 * 结算申请管理
 */
@RequestMapping("/${smartwork.verision}/sett-appl")
@Api(tags = {"结算申请管理"})
@Slf4j
public class SettApplApiProvider {

    @Autowired
    IZGSettApplService settApplService;
    @Autowired
    IZGEarningsService earningsService;

    /***
     *
     * @return
     */
    @ApiOperation("审核状态")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/review-status",method = RequestMethod.GET)
    public List<ResultEnum> reviewStatus(){
        return ReviewStatusEnum.resultEnums();
    }


    /***
     * @return
     */
    @ApiOperation("结算状态")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/settl-status",method = RequestMethod.GET)
    public List<ResultEnum> settlStatus(){
        return SettlStatusEnum.resultEnums();
    }


    /***
     *
     * @return
     */
    @ApiOperation("结算类型")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/settl-types",method = RequestMethod.GET)
    public List<ResultEnum> settlType(){
        return SettlTypeEnum.resultEnums();
    }


    /***
     *分页查询我的申请记录
     * @param pageDto
     * @param settAppl
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ApiOperation("分页查询我的申请记录")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ROLE_LIST_MSG)
    })
    public  Result<IPage<ZGSettAppl>> selectPage(BasePageDto pageDto,ZGSettAppl settAppl){
        log.debug("传入的参数为" + settAppl.toString());
        Result<IPage<ZGSettAppl>> result = new Result<>();
        QueryWrapper<ZGSettAppl> qw = new QueryWrapper<ZGSettAppl>();
        if(ConvertUtils.isNotEmpty(settAppl)){
            QueryWrapperUtils.combinationQueryWrapper(settAppl,qw);
        }
        qw.eq("user_id", UserContext.getSysUser().getId());
        IPage<ZGSettAppl> page = new Page<ZGSettAppl>(pageDto.getPageNo(),pageDto.getPageSize());
        IPage<ZGSettAppl> pageResult = settApplService.page(page,qw);
        result.setResult(pageResult);
        return result;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    @ApiOperation("申请结算")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ROLE_LIST_MSG)
    })
    public  Result<ZGSettAppl> applySett(@RequestBody ZGSettAppl settAppl){
        Result<ZGSettAppl> result = new Result<ZGSettAppl>();

        result.setResult(settAppl);
        return result;
    }




}
