package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.model.ZGCompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * 类概述:Api新建公司，修改，设置岗位管理
 * @创建人 Tom
 * @创建时间 2020/3/16
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/company")
@Api(tags = {"Api--创建公司-修改公司管理"})
@Slf4j
public class ZGCompanyApiProvider {

    @Autowired
    IZGCompanyService zgCompanyService;

    /***
     * addCompany方法概述:创建公司
     * @param zgCompanyDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCompanyDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/insert-company", method = RequestMethod.POST)
    @ApiOperation("创建公司")
    public Result<ZGCompanyDto> addCompany(@RequestBody @Validated(value = SaveValid.class) ZGCompanyDto zgCompanyDto) {
        Result<ZGCompanyDto> result=new Result<ZGCompanyDto>();
        zgCompanyService.addCompany(zgCompanyDto);
        result.setResult(zgCompanyDto);
        return result;
    }

    /***
     * updateCompany方法概述:公司信息修改
     * @param zgCompanyDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCompanyDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/alter-company", method = RequestMethod.PUT)
    @ApiOperation("公司信息修改")
    public Result<ZGCompanyDto> updateCompany(@RequestBody @Validated(value = SaveValid.class) ZGCompanyDto zgCompanyDto) {
        Result<ZGCompanyDto> result=new Result<ZGCompanyDto>();
        zgCompanyService.updateCompany(zgCompanyDto);
        result.setResult(zgCompanyDto);
        return result;
    }
}
