package org.smartwork.provider;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.utils.QueryWrapperUtils;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.ResultEnum;
import org.smartwork.biz.service.IZGEarningsService;
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.enums.ReviewStatusEnum;
import org.smartwork.comm.enums.SettlStatusEnum;
import org.smartwork.comm.enums.SettlTypeEnum;
import org.smartwork.dal.entity.ZGSettAppl;
import org.smartwork.service.IMchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
    @Autowired
    IMchInfoService mchInfoService;


    /****
     * 查询待结算记录
     * @param pageDto
     * @return
     */
    @RequestMapping(value = "/select-sett-list", method = RequestMethod.GET)
    @ApiOperation("分页查询我的申请记录")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ROLE_LIST_MSG)
    })
    public  Result<IPage<ZGSettAppl>> selectSettList(BasePageDto pageDto){
        Result<IPage<ZGSettAppl>> result = new Result<>();
        IPage<ZGSettAppl> page = new Page<ZGSettAppl>(pageDto.getPageNo(),pageDto.getPageSize());
        IPage<ZGSettAppl> pageResult = settApplService.pageTransfer(page,ReviewStatusEnum.SUCCEED.getCode(),SettlStatusEnum.NO_SETTL.getCode());
        result.setResult(pageResult);
        return result;
    }


    /***更新为转帐中
     * @param ids
     * @return
     */
    @RequestMapping(value = "/up-in-settl", method = RequestMethod.PUT)
    @ApiImplicitParam(name = "ids",value = "申请记录结果集")
    @ApiOperation("更新记录为转帐中")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ROLE_LIST_MSG)
    })
    public  Result<Boolean> upInSettl(@RequestParam(value = "ids",required = true)List<Long>  ids){
        Result<Boolean> result = new Result<>();
        settApplService.update(new UpdateWrapper<ZGSettAppl>().set("settl_status",SettlStatusEnum.IN_SETTL.getCode())
                .in("id",ids)
                .eq("review_status", ReviewStatusEnum.SUCCEED.getCode())
                .eq("settl_status", SettlStatusEnum.NO_SETTL.getCode()));
        result.setResult(true);
        return result;
    }


    /***
     * 更新失败原因
     * @param id
     * @param failure
     * @return
     */
    @RequestMapping(value = "/up-failure-settl", method = RequestMethod.PUT)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id",value = "主键ID"),
            @ApiImplicitParam(name = "failure",value = "失败原因")
    })
    @ApiOperation("更新记录为转帐中")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ROLE_LIST_MSG)
    })
    public  Result<Boolean> upfailureSettl(@RequestParam(value = "id",required = true)Long  id,
                                           @RequestParam(value = "failure",required = true)String failure){
        Result<Boolean> result = new Result<>();
        ZGSettAppl settAppl = settApplService.getById(id);
        if(ConvertUtils.isEmpty(settAppl)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        if(!ReviewStatusEnum.SUCCEED.getCode().equalsIgnoreCase(settAppl.getReviewStatus())){
            result.setBizCode(MemberBizResultEnum.NOT_AUDIT.getBizCode());
            result.setMessage(MemberBizResultEnum.NOT_AUDIT.getBizMessage());
            return result;
        }
        if(!SettlStatusEnum.IN_SETTL.getCode().equalsIgnoreCase(settAppl.getSettlStatus())){
            result.setBizCode(MemberBizResultEnum.SETTL_STATUS.getBizCode());
            result.setMessage(MemberBizResultEnum.SETTL_STATUS.getBizMessage());
            return result;
        }
        Integer  failureCount = settAppl.getFailureCount();
        failureCount += 1;
        Integer retryCont = settAppl.getRetryCount();
        UpdateWrapper updateWrapper =  new UpdateWrapper<ZGSettAppl>();
        int  compareFailure = failureCount.compareTo(retryCont);
        if( compareFailure < 0){
            updateWrapper.set("settl_status",SettlStatusEnum.NO_SETTL.getCode());
            updateWrapper.set("failure_count",failureCount);
        }
        if(compareFailure ==0 ){
            updateWrapper.set("settl_status",SettlStatusEnum.ABNORMAL.getCode());
            updateWrapper.set("failure_count",failureCount);
        }
        if(compareFailure > 0 ){
            updateWrapper.set("settl_status",SettlStatusEnum.ABNORMAL.getCode());
        }
        updateWrapper.set("failure",failure);
        updateWrapper.eq("id",id);
        result.setResult(true);
        return result;
    }





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
    @ApiOperation("结算方式")
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


    /****
     * 计算实际结算金额
     * @param settlType
     * @param amount
     * @return
     */
    @RequestMapping(value = "/calculate-amount", method = RequestMethod.POST)
    @ApiOperation("计算实际结算金额")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "settlType",value = "结算方式"),
            @ApiImplicitParam(name = "amount",value = "申请结算金额")
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = "platDedAmount-平台扣款金额,actualAmount-实际结算金额")
    })
    public  Result<Map<String,BigDecimal>> calculateAmount(@RequestParam(value = "settlType",required = true)String settlType,
                                               @RequestParam(value = "amount",required = true)BigDecimal amount){
        Result<Map<String,BigDecimal>> result = new Result<Map<String,BigDecimal>>();
        Map<String,BigDecimal> resultMap = Maps.newHashMap();
        if(BigDecimal.ZERO.compareTo(amount) > 0){
            result.setBizCode(MemberBizResultEnum.ZERO_AMOUNT.getBizCode());
            result.setMessage(MemberBizResultEnum.ZERO_AMOUNT.getBizMessage());
            return  result;
        }
        if(!SettlTypeEnum.existsCode(settlType)){
            result.setBizCode(MemberBizResultEnum.SETTL_TYPE.getBizCode());
            result.setMessage(MemberBizResultEnum.SETTL_TYPE.getBizMessage());
            return result;
        }
        Result<Map<String,Object>> mchInfoResult = mchInfoService.channelMchInfo(SettlTypeEnum.receChannel(settlType));
        Map<String,Object> mchinfoMap = mchInfoResult.getResult();
        if(ConvertUtils.isNotEmpty(mchinfoMap)){
            String mchId = (String) mchinfoMap.get("mchId");
            BigDecimal reflectPoints = (BigDecimal) mchinfoMap.get("reflectPoints");
            BigDecimal platDedAmount = amount.multiply(reflectPoints);
            BigDecimal  actualAmount = amount.subtract(platDedAmount);
            resultMap.put("platDedAmount",platDedAmount);
            resultMap.put("actualAmount",actualAmount);
            result.setResult(resultMap);
        } else {
            log.error(mchInfoResult.toString());
            result.setBizCode(mchInfoResult.getBizCode());
            result.setMessage(mchInfoResult.getMessage());
            result.setCode(mchInfoResult.getCode());
        }
        return result;
    }

    /***结算申请
     * @param settAppl
     * @return
     */
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ApiOperation("申请结算")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ROLE_LIST_MSG)
    })
    public  Result<ZGSettAppl> applySett(@RequestBody ZGSettAppl settAppl){
        Result<ZGSettAppl> result = new Result<ZGSettAppl>();
        if(BigDecimal.ZERO.compareTo(settAppl.getAmount()) > 0){
            result.setBizCode(MemberBizResultEnum.ZERO_AMOUNT.getBizCode());
            result.setMessage(MemberBizResultEnum.ZERO_AMOUNT.getBizMessage());
            return  result;
        }
        if(ConvertUtils.isEmpty(settAppl.getSettlType())
                || !SettlTypeEnum.existsCode(settAppl.getSettlType())){
            result.setBizCode(MemberBizResultEnum.SETTL_TYPE.getBizCode());
            result.setMessage(MemberBizResultEnum.SETTL_TYPE.getBizMessage());
            return result;
        }
        if(ConvertUtils.isEmpty(settAppl.getSettlAccount())){
            result.setBizCode(MemberBizResultEnum.ACCOUNT.getBizCode());
            result.setMessage(MemberBizResultEnum.ACCOUNT.getBizMessage());
            return result;
        }
        if(ConvertUtils.isEmpty(settAppl.getAccountName())){
            result.setBizCode(MemberBizResultEnum.ACCOUNT_NAME.getBizCode());
            result.setMessage(MemberBizResultEnum.ACCOUNT_NAME.getBizMessage());
            return result;
        }
        Result<Map<String,Object>> mchInfoResult = mchInfoService.channelMchInfo(SettlTypeEnum.receChannel(settAppl.getSettlType()));
        Map<String,Object> mchinfoMap = mchInfoResult.getResult();
        if(ConvertUtils.isNotEmpty(mchinfoMap)){
            String mchId = (String) mchinfoMap.get("mchId");
            BigDecimal reflectPoints = (BigDecimal) mchinfoMap.get("reflectPoints");
            settApplService.applySett(settAppl,mchId,reflectPoints);
        } else {
            log.error(mchInfoResult.toString());
            result.setBizCode(mchInfoResult.getBizCode());
            result.setMessage(mchInfoResult.getMessage());
            result.setCode(mchInfoResult.getCode());
        }
        result.setResult(settAppl);
        return result;
    }
}
