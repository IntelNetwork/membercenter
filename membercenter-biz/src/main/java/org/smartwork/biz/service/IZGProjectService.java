package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.smartwork.comm.model.ZGProjectDto;
import org.smartwork.dal.entity.ZGProject;

public interface IZGProjectService extends IService<ZGProject> {
    /***
     * createPro方法概述:创建项目
     * @param projectDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void createPro(ZGProjectDto projectDto);

    /***
     * modifyPro方法概述:编辑项目
     * @param projectDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void modifyPro(ZGProjectDto projectDto);
}