package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.forbes.comm.enums.ActivityStateEnum;
import org.forbes.comm.enums.DeadUnitEnum;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.ResultEnum;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGMemberLevelService;
import org.smartwork.dal.entity.ZGMemberLevel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags={"会员等级管理"})
@RestController
@RequestMapping("/zgmemberlevel")
public class ZGMemberLevelController extends BaseProvider<IZGMemberLevelService, ZGMemberLevel> {


    /***
     *
     * @return
     */
    @ApiOperation("期限单位")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/deadunits",method = RequestMethod.GET)
    public List<ResultEnum> channelName(){
        return DeadUnitEnum.resultEnums();
    }


    /***
     * 启用状态
     * @return
     */
    @ApiOperation("启用状态")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/activity-state",method = RequestMethod.GET)
    public List<ResultEnum> activityStates(){
        return ActivityStateEnum.resultEnums();
    }




}