package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.mapper.ZGCmRelUserMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGCmRelUserServiceImpl extends ServiceImpl<ZGCmRelUserMapper, ZGCmRelUser> implements IZGCmRelUserService {
}