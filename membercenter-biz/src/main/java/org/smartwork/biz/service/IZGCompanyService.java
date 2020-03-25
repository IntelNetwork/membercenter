package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.smartwork.comm.model.ZGCompanyDto;
import org.smartwork.dal.entity.ZGCompany;

public interface IZGCompanyService extends IService<ZGCompany> {

    /***
     * addCompany方法概述:创建公司
     * @param zgCompanyDto
     * @创建人 nhy
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addCompany(ZGCompanyDto zgCompanyDto);

    /***
     * updateCompany方法概述:公司信息修改
     * @param zgCompanyDto
     * @创建人 nhy
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void updateCompany(ZGCompanyDto zgCompanyDto);
}