package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTeamRelUserService;
import org.smartwork.comm.model.ZGTeamRelUserDto;
import org.smartwork.dal.entity.ZGTeamRelUser;
import org.smartwork.dal.mapper.ZGTeamRelUserMapper;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZGTeamRelUserServiceImpl extends ServiceImpl<ZGTeamRelUserMapper, ZGTeamRelUser> implements IZGTeamRelUserService {


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

}