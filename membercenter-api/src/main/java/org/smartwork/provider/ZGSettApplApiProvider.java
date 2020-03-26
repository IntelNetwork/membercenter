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
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.comm.constant.ZGRevenueRecordConstant;
import org.smartwork.comm.constant.ZGSettApplConstant;
import org.smartwork.comm.model.ZGSettApplPageDto;
import org.smartwork.dal.entity.ZGSettAppl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * 概述:佣金提现申请(佣金管理)
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/26
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/sett-appl")
@Api(tags = {"API--佣金提现申请(佣金管理)"})
@Slf4j
public class ZGSettApplApiProvider {

    @Autowired
    IZGSettApplService settApplService;

    /***
     * 方法概述:佣金提现申请分页查询
     * @param pageDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/26
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/settAppls", method = RequestMethod.GET)
    @ApiOperation("佣金提现申请分页查询")
    public Result<IPage<ZGSettAppl>> list(BasePageDto basePageDto, ZGSettApplPageDto pageDto) {
        Result<IPage<ZGSettAppl>> result = new Result<>();
        QueryWrapper<ZGSettAppl> qw = new QueryWrapper<>();
        if (ConvertUtils.isNotEmpty(pageDto)) {
            if (ConvertUtils.isNotEmpty(pageDto.getTitle())) {
                qw.like(ZGSettApplConstant.TITLE, pageDto.getTitle());
            } if (ConvertUtils.isNotEmpty(pageDto.getApplOrderNo())) {
                qw.like(ZGSettApplConstant.APPL_ORDER_NO, pageDto.getApplOrderNo());
            }
            if (ConvertUtils.isNotEmpty(pageDto.getReviewStatus())) {
                qw.eq(ZGSettApplConstant.REVIEW_STATUS, pageDto.getReviewStatus());
            }
        }
        IPage<ZGSettAppl> page = new Page<>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ZGSettAppl> pages = settApplService.page(page, qw);
        result.setResult(pages);
        return result;
    }
}
