package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * 概述:公司附件
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/25
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/attach")
@Api(tags = {"Api--公司附件设置"})
@Slf4j
public class ZGCmAttachApiprovider {

    @Autowired
    IZGCmAttachService cmAttachService;


    /***
     * deleteAttach方法概述:公司删除附件
     * @param id
     * @创建人 nhy
     * @创建时间 2020/3/18 17:16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    @ApiOperation("公司删除附件")
    public Result<Boolean> deletePost(@RequestParam(name = "id") Long id) {
        Result<Boolean> result = new Result<>();

        Boolean del = cmAttachService.removeById(id);
        result.setResult(del);
        return result;
    }

}
