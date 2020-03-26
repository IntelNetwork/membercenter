package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmAttachService;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.entity.ZGCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * 概述:公司附件
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/25
 * @修改人 (修改了该文件 ， 请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/attach")
@Api(tags = {"Api--公司附件设置"})
@Slf4j
public class ZGCmAttachApiprovider {
    @Autowired
    IZGCmAttachService cmAttachService;
    @Autowired
    IZGCmRelUserService cmRelUserService;
    @Autowired
    IZGCompanyService companyService;


    /***
     * deleteAttach方法概述:公司删除附件
     * @param id
     * @创建人 nhy
     * @创建时间 2020/3/18 17:16
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ApiOperation("公司删除附件")
    public Result<Boolean> deletePost(@RequestParam(name = "id") Long id) {
        Result<Boolean> result = new Result<>();
        //对比当前操作人是否是公司负责人
        SysUser user = UserContext.getSysUser();
        ZGCmRelUser zgCmRelUser = cmRelUserService.getOne(new QueryWrapper<ZGCmRelUser>().eq("user_id", user.getId()));
        ZGCompany company = companyService.getById(zgCmRelUser.getCmId());
        if (!company.getLegalPerson().equals(user.getRealname())) {
            result.setBizCode(MemberBizResultEnum.NO_PERMISSION_TO_CM.getBizCode());
            result.setMessage(MemberBizResultEnum.NO_PERMISSION_TO_CM.getBizMessage());
            return result;
        }
        Boolean del = cmAttachService.removeById(id);
        result.setResult(del);
        return result;
    }

}
