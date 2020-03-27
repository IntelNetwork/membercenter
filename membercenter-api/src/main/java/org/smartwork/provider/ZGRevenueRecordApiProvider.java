package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGRevenueRecordService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGRevenueRecordPageDto;
import org.smartwork.dal.entity.ZGRevenueRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * 概述:个人营收记录(佣金管理)
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/26
 * @修改人 (修改了该文件 ， 请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/rev")
@Api(tags = {"API--个人营收记录(佣金管理)"})
@Slf4j
public class ZGRevenueRecordApiProvider {

    @Autowired
    IZGRevenueRecordService revenueRecordService;


    /***
     * 方法概述:佣金记录分页查询
     * @param pageDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/26
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/revs", method = RequestMethod.GET)
    @ApiOperation("佣金记录分页查询")
    public Result<IPage<ZGRevenueRecord>> list(BasePageDto basePageDto, ZGRevenueRecordPageDto pageDto) {
        Result<IPage<ZGRevenueRecord>> result = new Result<>();
        QueryWrapper<ZGRevenueRecord> qw = new QueryWrapper<>();
        if (ConvertUtils.isNotEmpty(pageDto.getUserName())) {
            qw.like("user_name", pageDto.getUserName());
        }
        if (ConvertUtils.isNotEmpty(pageDto)) {
            if (ConvertUtils.isNotEmpty(pageDto.getSourceTitle())) {
                qw.like("source_title", pageDto.getSourceTitle());
            }
            if (ConvertUtils.isNotEmpty(pageDto.getDataType())) {
                qw.eq("data_type", pageDto.getDataType());
            }
        }
        IPage<ZGRevenueRecord> page = new Page<>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ZGRevenueRecord> pages = revenueRecordService.page(page, qw);
        result.setResult(pages);
        return result;
    }


    /***
     * 方法概述:查看佣金记录详情
     * @param id
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/26
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/rev-details", method = RequestMethod.GET)
    @ApiOperation("查看佣金记录详情")
    public Result<ZGRevenueRecord> list(@RequestParam(value = "id") Long id) {
        Result<ZGRevenueRecord> result = new Result<>();
        if (ConvertUtils.isEmpty(id)) {
            result.setBizCode(MemberBizResultEnum.EMPTY.getBizCode());
            result.setMessage(MemberBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        ZGRevenueRecord rev = revenueRecordService.getById(id);
        result.setResult(rev);
        return result;
    }


}
