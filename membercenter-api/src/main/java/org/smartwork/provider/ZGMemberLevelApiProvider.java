package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.enums.ActivityStateEnum;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGMemberLevelOrderService;
import org.smartwork.biz.service.IZGMemberLevelPermEleService;
import org.smartwork.biz.service.IZGMemberLevelService;
import org.smartwork.dal.entity.ZGMemberLevel;
import org.smartwork.dal.entity.ZGMemberLevelOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/${smartwork.verision}/member-level")
@Api(tags = {"前端会员等级管理"})
@Slf4j
<<<<<<< HEAD:membercenter-api/src/main/java/org/smartwork/provider/MemberLevelApiProvider.java
public class MemberLevelApiProvider {



    @Autowired
    IZGMemberLevelService memberLevelService;
    @Autowired
    IZGMemberLevelOrderService memberLevelOrderService;


    /****
     * 查询所有已启用等级
     * @return
     */
    @RequestMapping(value = "/lists",method = RequestMethod.GET)
    @ApiOperation("查询所有已启用等级")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.ROLE_LIST_MSG),
            @ApiResponse(code = 500,message = Result.ROLE_LIST_ERROR_MSG)
    })
    public Result<List<ZGMemberLevel>> selecAlls(){
        Result<List<ZGMemberLevel>> result=new Result<>();
        List<ZGMemberLevel> allLists = memberLevelService.list(new QueryWrapper<ZGMemberLevel>().eq("state", ActivityStateEnum.ACTIVITY.getCode()));
        result.setResult(allLists);
        return result;
    }


    /***
     * 会员等级升级
     * @param bid
     * @return
     */
    @ApiOperation("会员等级升级")
    @ApiImplicitParam(name = "bid",value = "等级业务ID")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/upgrade-member-level",method = RequestMethod.PUT)
    public  Result<Map<String,Object>> upgradeMemberlevel(@RequestParam(value = "bid",required = true)String bid){
        Result<Map<String,Object>> result = new Result<Map<String,Object>>();
        Map<String,Object> resultMap = Maps.newHashMap();
        try {
            ZGMemberLevel  memberLevel = memberLevelService.getOne(new QueryWrapper<ZGMemberLevel>()
                    .eq("bid",bid));
            if (ConvertUtils.isNotEmpty(memberLevel)){
                ZGMemberLevelOrder memberLevelOrder = memberLevelOrderService.createLevelOrder(memberLevel);
                resultMap.put("mchOrderNo",memberLevelOrder.getMlOrderNo());
                resultMap.put("amount",memberLevelOrder.getPayAmount());
                resultMap.put("currency","cny");
                resultMap.put("subject",memberLevel.getName());
                resultMap.put("body",memberLevel.getName());
                resultMap.put("notifyUrl","topicMemberlevel");
            } else {
                result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
                result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            }
            result.setResult(resultMap);
        } catch (Exception e) {
            result.error500(e.getMessage());
        }
        return result;
    }
=======
public class ZGMemberLevelApiProvider {
>>>>>>> 326716a99cfd0ff9e7783400ed76c0ae9674ba09:membercenter-api/src/main/java/org/smartwork/provider/ZGMemberLevelApiProvider.java
}
