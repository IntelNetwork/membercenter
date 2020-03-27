package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.biz.service.IZGProjectPerService;
import org.smartwork.biz.service.IZGTeamRelUserService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.dal.entity.ZGProjectPer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${smartwork.verision}/pro-per")
@Api(tags = {"API--项目中人员岗位管理"})
@Slf4j
public class ZGProjectPerProvider {

    @Autowired
    IZGProjectPerService projectPerService;
    @Autowired
    IZGCmRelUserService cmRelUserService;
    @Autowired
    IZGTeamRelUserService teamRelUserService;


    /***
     * 方法概述:项目人员岗位分配
     * @param projectPers
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/27
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/distribution", method = RequestMethod.PUT)
    @ApiOperation("项目人员岗位分配")
    public Result<List<ZGProjectPer>> distribution(List<ZGProjectPer> projectPers) {
        Result<List<ZGProjectPer>> result = new Result<>();
        if (ConvertUtils.isEmpty(projectPers)) {
            result.setBizCode(MemberBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        projectPers.forEach(projectPer -> {
            projectPerService.save(projectPer);
        });
        return result;
    }


}
