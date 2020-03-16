package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGProjectService;
import org.smartwork.dal.entity.ZGProject;
import org.smartwork.dal.mapper.ZGProjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGProjectServiceImpl extends ServiceImpl<ZGProjectMapper, ZGProject> implements IZGProjectService {
}