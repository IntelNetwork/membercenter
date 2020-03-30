package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGLevelPermNameService;
import org.smartwork.dal.entity.ZGLevelPermName;
import org.smartwork.dal.mapper.ZGLevelPermNameMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGLevelPermNameServiceImpl extends ServiceImpl<ZGLevelPermNameMapper, ZGLevelPermName> implements IZGLevelPermNameService {
}