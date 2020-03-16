package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTeamAttachService;
import org.smartwork.dal.entity.ZGTeamAttach;
import org.smartwork.dal.mapper.ZGTeamAttachMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTeamAttachServiceImpl extends ServiceImpl<ZGTeamAttachMapper, ZGTeamAttach> implements IZGTeamAttachService {
}