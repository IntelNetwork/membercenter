package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTeamService;
import org.smartwork.dal.entity.ZGTeam;
import org.smartwork.dal.mapper.ZGTeamMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTeamServiceImpl extends ServiceImpl<ZGTeamMapper, ZGTeam> implements IZGTeamService {
}