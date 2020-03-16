package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTeamRelUserService;
import org.smartwork.dal.entity.ZGTeamRelUser;
import org.smartwork.dal.mapper.ZGTeamRelUserMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTeamRelUserServiceImpl extends ServiceImpl<ZGTeamRelUserMapper, ZGTeamRelUser> implements IZGTeamRelUserService {
}