package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.service.ISysUserService;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTeamRelUserService;
import org.smartwork.comm.constant.TeamRelUserCommonConstant;
import org.smartwork.comm.model.ZGTeamRelUserDto;
import org.smartwork.comm.vo.ZGTeamRelUserVo;
import org.smartwork.dal.entity.ZGTeamRelUser;
import org.smartwork.dal.mapper.ZGTeamRelUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZGTeamRelUserServiceImpl extends ServiceImpl<ZGTeamRelUserMapper, ZGTeamRelUser> implements IZGTeamRelUserService {

    @Autowired
    ZGTeamRelUserMapper teamRelUserMapper;

    @Autowired
    ISysUserService sysUserService;


    /***
     * addTeamRelUser方法概述:团队任务分配
     * @param teamRelUserDtos
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTeamRelUser(List<ZGTeamRelUserDto> teamRelUserDtos) {
        teamRelUserDtos.forEach(teamRelUserDto -> {
            ZGTeamRelUser teamRelUser = new ZGTeamRelUser();
            BeanCopier.create(ZGTeamRelUserDto.class, ZGTeamRelUser.class, false)
                    .copy(teamRelUserDto, teamRelUser, null);
            baseMapper.insert(teamRelUser);

        });
    }



    /***
     * teamUserDetail方法概述:团队查看员工详情
     * @param teamId,userName
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/23
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public ZGTeamRelUserVo teamUserDetail(Long teamId, String userName) {
        QueryWrapper<ZGTeamRelUser> qw = new QueryWrapper<>();
        qw.eq(TeamRelUserCommonConstant.TEAM_TEAM_ID, teamId);
        qw.eq(TeamRelUserCommonConstant.TEAM_USER_NAME, userName);
        ZGTeamRelUser teamRelUser = teamRelUserMapper.selectOne(qw);
        Result<SysUser> user = sysUserService.getUserByName(userName);
        ZGTeamRelUserVo teamRelUserVo = new ZGTeamRelUserVo();

        BeanCopier.create(ZGTeamRelUser.class, ZGTeamRelUserVo.class, false)
                .copy(teamRelUser, teamRelUserVo, null);
        teamRelUserVo.setUser(user);
        return teamRelUserVo;
    }

}