package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.vo.Result;
import org.smartwork.comm.model.ZGTeamDto;
import org.smartwork.dal.entity.ZGTeam;

public interface IZGTeamService extends IService<ZGTeam> {

    /***
     * addTeam方法概述:创建公司
     * @param teamDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
   void addTeam(ZGTeamDto teamDto);
}