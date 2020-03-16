package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.comm.model.ZGTeamDto;
import org.smartwork.dal.entity.ZGTeam;
import org.smartwork.dal.mapper.ZGTeamMapper;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

@Service
public class ZGTeamServiceImpl extends ServiceImpl<ZGTeamMapper, ZGTeam> implements IZGTeamService {



    /***
     * addTeam方法概述:创建公司
     * @param teamDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public void addTeam(ZGTeamDto teamDto) {
        ZGTeam team = new ZGTeam();
        BeanCopier.create(ZGTeamDto.class, ZGTeam.class, false)
                .copy(teamDto, team, null);


    }
}