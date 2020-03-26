package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.*;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.ResultEnum;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.enums.ReviewStatusEnum;
import org.smartwork.comm.enums.SettlStatusEnum;
import org.smartwork.comm.enums.SettlTypeEnum;
import org.smartwork.dal.entity.ZGSettAppl;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags={"结算申请管理"})
@RestController
@RequestMapping("/zgsettappl")
public class ZGSettApplController extends BaseProvider<IZGSettApplService, ZGSettAppl> {


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
     * 结算审核
     * @param settApplId
     * @param reviewNote
     * @param payCred
     * @param reviewStatus
     * @return
     */
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "settApplId",value = "结算ID"),
            @ApiImplicitParam(name = "reviewNote",value = "审核备注"),
            @ApiImplicitParam(name = "settlType",value = "结算方式（注：后台审核时只允许修改为线下支付）"),
            @ApiImplicitParam(name = "payCred",value = "支付凭证（注：线下支付必须传支付凭证）"),
            @ApiImplicitParam(name = "reviewStatus",value = "审核状态"),
    })
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ApiOperation("结算审核")
    public Result<ZGSettAppl> auditSettAppl(@RequestParam(value = "settApplId",required = true)Long settApplId,
                                            @RequestParam(value = "reviewNote",required = false)String reviewNote,
                                            @RequestParam(value = "settlType",required = false)String settlType,
                                            @RequestParam(value = "payCred",required = false)String payCred,
                                            @RequestParam(value = "reviewStatus",required = false)String reviewStatus) {
        Result<ZGSettAppl>  result = new Result<ZGSettAppl>();
        SysUser sysUser = UserContext.getSysUser();
        ZGSettAppl settAppl = baseService.getById(settApplId);
        if(ConvertUtils.isEmpty(settAppl)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return  result;
        }
        if(!ReviewStatusEnum.existsCode(reviewStatus)){
            result.setBizCode(MemberBizResultEnum.REVIEW_STATUS_EXISTS.getBizCode());
            result.setMessage(String.format(MemberBizResultEnum.REVIEW_STATUS_EXISTS.getBizFormateMessage(),reviewStatus));
            return  result;
        }
        /**审核方式**/
        if(!ReviewStatusEnum.AUDIT.getCode().equalsIgnoreCase(settAppl.getReviewStatus())){
            result.setBizCode(MemberBizResultEnum.REVIEW_STATUS_EXISTS.getBizCode());
            result.setMessage(String.format(MemberBizResultEnum.REVIEW_STATUS_EXISTS.getBizFormateMessage(),settAppl.getTitle()));
            return  result;
        }
        /***审核时结算方式只允许修改为线下支付**/
        if(ConvertUtils.isNotEmpty(settlType)
                && !SettlTypeEnum.OFFLINE.getCode().equalsIgnoreCase(settlType)){
            result.setBizCode(MemberBizResultEnum.AUDIT_WAY.getBizCode());
            result.setMessage(String.format(MemberBizResultEnum.AUDIT_WAY.getBizFormateMessage(),settAppl.getTitle()));
            return  result;
        }
        UpdateWrapper<ZGSettAppl>  uwSettAppl = new UpdateWrapper<ZGSettAppl>();
        uwSettAppl.set("review_status",reviewStatus);
        if(SettlTypeEnum.OFFLINE.getCode().equalsIgnoreCase(settAppl.getSettlType())
                || (ConvertUtils.isNotEmpty(settlType)
                && SettlTypeEnum.OFFLINE.getCode().equalsIgnoreCase(settlType))){
            if(ConvertUtils.isEmpty(payCred)){
                result.setBizCode(MemberBizResultEnum.PAY_CRED_NOT_EXISTS.getBizCode());
                result.setMessage(MemberBizResultEnum.PAY_CRED_NOT_EXISTS.getBizMessage());
                return  result;
            }
            uwSettAppl.set("settl_status",SettlStatusEnum.HAS_SETTL.getCode());
        } else {
            uwSettAppl.set("settl_status",SettlStatusEnum.NO_SETTL.getCode());
        }
        if(ConvertUtils.isNotEmpty(reviewNote)){
            uwSettAppl.set("review_note",reviewNote);
        }
        if(ConvertUtils.isNotEmpty(payCred)){
            uwSettAppl.set("pay_cred",payCred);
        }
        uwSettAppl.set("reviewer",sysUser.getRealname());
        uwSettAppl.set("reviewer_time",new Date());
        uwSettAppl.eq("id",settApplId);
        baseService.update(uwSettAppl);
        return result;
    }


}