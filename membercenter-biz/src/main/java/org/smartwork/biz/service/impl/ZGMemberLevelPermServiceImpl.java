package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGMemberLevelPermService;
import org.smartwork.dal.entity.ZGMemberLevelPerm;
import org.smartwork.dal.mapper.ZGMemberLevelPermMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGMemberLevelPermServiceImpl extends ServiceImpl<ZGMemberLevelPermMapper, ZGMemberLevelPerm> implements IZGMemberLevelPermService {
}