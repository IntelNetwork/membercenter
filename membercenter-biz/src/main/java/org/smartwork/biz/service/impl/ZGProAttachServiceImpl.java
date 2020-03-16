package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGProAttachService;
import org.smartwork.dal.entity.ZGProAttach;
import org.smartwork.dal.mapper.ZGProAttachMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGProAttachServiceImpl extends ServiceImpl<ZGProAttachMapper, ZGProAttach> implements IZGProAttachService {
}