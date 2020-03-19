package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.comm.model.ZGTeamAttachDto;
import org.smartwork.comm.model.ZGTeamDto;
import org.smartwork.comm.model.ZGTeamRelUserDto;
import org.smartwork.dal.entity.ZGTeam;
import org.smartwork.dal.entity.ZGTeamAttach;
import org.smartwork.dal.entity.ZGTeamRelUser;
import org.smartwork.dal.mapper.ZGTeamAttachMapper;
import org.smartwork.dal.mapper.ZGTeamMapper;
import org.smartwork.dal.mapper.ZGTeamRelUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZGTeamServiceImpl extends ServiceImpl<ZGTeamMapper, ZGTeam> implements IZGTeamService {

    /**
     * 团队附件
     */
    @Autowired
    ZGTeamAttachMapper teamAttachMapper;
    /**
     * 团队用户
     */
    @Autowired
    ZGTeamRelUserMapper teamRelUserMapper;


    /***
     * addTeam方法概述:创建公司
     * @param teamDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTeam(ZGTeamDto teamDto) {
        ZGTeam team = new ZGTeam();
        BeanCopier.create(ZGTeamDto.class, ZGTeam.class, false)
                .copy(teamDto, team, null);
        baseMapper.insert(team);

        //关联团队附件
        List<ZGTeamAttachDto> zgTaskAttachDtos = teamDto.getTeamAttachDtos();
        if (ConvertUtils.isNotEmpty(zgTaskAttachDtos)) {
            Long teamId = team.getId();
            ZGTeamAttach attach = new ZGTeamAttach();
            zgTaskAttachDtos.stream().forEach(temp -> {
                attach.setDataId(teamId);
                attach.setCnName(temp.getCnName());
                attach.setFilePath(temp.getFilePath());
                attach.setSuffix(temp.getSuffix());
                attach.setDataType(temp.getDataType());
                attach.setOrders(temp.getOrders());
                //执行添加
                teamAttachMapper.insert(attach);
            });
        }

        //关联团队用户
        List<ZGTeamRelUserDto> teamRelUserDtos = teamDto.getTeamRelUserDtos();
        if (ConvertUtils.isNotEmpty(teamRelUserDtos)) {
            Long teamId = team.getId();
            ZGTeamRelUser teamUser = new ZGTeamRelUser();
            teamRelUserDtos.stream().forEach(temp -> {
                teamUser.setTeamId(teamId);
                teamUser.setUserId(temp.getUserId());
                teamUser.setAdminFlag(temp.getAdminFlag());
                teamUser.setDirection(temp.getDirection());
                teamUser.setUserName(temp.getUserName());
                //执行添加
                teamRelUserMapper.insert(teamUser);
            });
        }
    }


    /***
     * editTeam方法概述:编辑团队
     * @param teamDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editTeam(ZGTeamDto teamDto) {
        ZGTeam team = new ZGTeam();
        BeanCopier.create(ZGTeamDto.class, ZGTeam.class, false)
                .copy(teamDto, team, null);
        baseMapper.updateById(team);


        //关联编辑团队附件
        List<ZGTeamAttachDto> zgTaskAttachDtos = teamDto.getTeamAttachDtos();
        if (ConvertUtils.isNotEmpty(zgTaskAttachDtos)) {
            Long teamId = team.getId();
            ZGTeamAttach attach = new ZGTeamAttach();
            zgTaskAttachDtos.stream().forEach(temp -> {
                attach.setId(temp.getId());
                attach.setDataId(teamId);
                attach.setCnName(temp.getCnName());
                attach.setFilePath(temp.getFilePath());
                attach.setSuffix(temp.getSuffix());
                attach.setDataType(temp.getDataType());
                attach.setOrders(temp.getOrders());
                //执行编辑
                teamAttachMapper.updateById(attach);
            });
        }

    }
}