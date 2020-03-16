package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGMemberLevelService;
import org.smartwork.dal.entity.ZGMemberLevel;
import org.smartwork.dal.mapper.ZGMemberLevelMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGMemberLevelServiceImpl extends ServiceImpl<ZGMemberLevelMapper, ZGMemberLevel> implements IZGMemberLevelService {
}