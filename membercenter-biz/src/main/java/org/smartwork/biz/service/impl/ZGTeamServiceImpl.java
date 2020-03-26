package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.comm.enums.CmAdminFlagEnum;
import org.smartwork.comm.model.ZGTeamAttachDto;
import org.smartwork.comm.model.ZGTeamDto;
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

import java.io.Serializable;
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
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTeam(ZGTeamDto teamDto) {
        ZGTeam team = new ZGTeam();
        BeanCopier.create(ZGTeamDto.class, ZGTeam.class, false)
                .copy(teamDto, team, null);

        baseMapper.insert(team);
        //将创建人加入团队,默认管理员
        ZGTeamRelUser teamRelUser = new ZGTeamRelUser();
        SysUser user = UserContext.getSysUser();
        teamRelUser.setTeamId(team.getId());
        teamRelUser.setUserId(user.getId());
        teamRelUser.setAdminFlag(CmAdminFlagEnum.SUPER_ADMIN.getCode());
        teamRelUser.setUserName(user.getUsername());
        teamRelUserMapper.insert(teamRelUser);

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

    }


    /***
     * editTeam方法概述:编辑团队
     * @param teamDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
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

    /***
     * 方法概述:删除团队并删除团队中用户
     * @param id
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/23
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {

        teamRelUserMapper.deleteById(new QueryWrapper<ZGTeamRelUser>().eq("team_id", id));

        return true;
    }
}