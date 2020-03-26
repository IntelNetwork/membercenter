package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGEarningsService;
import org.smartwork.dal.entity.ZGEarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * 收益
 */
@RequestMapping("/${smartwork.verision}/earnings")
@Api(tags = {"收益管理"})
@Slf4j
public class EarningsApiProvider {


    @Autowired
    IZGEarningsService earningsService;

    /***
     *我的收益
     * @return
     */
    @ApiOperation("我的收益")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/my-earnings",method = RequestMethod.GET)
    public Result<ZGEarnings> myEarnings(){
        Result<ZGEarnings> result = new Result<>();
        SysUser sysUser = UserContext.getSysUser();
        ZGEarnings earnings =  earningsService.getOne(new QueryWrapper<ZGEarnings>().eq("user_id",sysUser.getId()));
        result.setResult(earnings);
        return result;
    }
}
