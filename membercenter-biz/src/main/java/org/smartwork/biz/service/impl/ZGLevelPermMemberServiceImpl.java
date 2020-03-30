package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGLevelPermMemberService;
import org.smartwork.dal.entity.ZGLevelPermMember;
import org.smartwork.dal.mapper.ZGLevelPermMemberMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGLevelPermMemberServiceImpl extends ServiceImpl<ZGLevelPermMemberMapper, ZGLevelPermMember> implements IZGLevelPermMemberService {
}