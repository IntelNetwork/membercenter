package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGCmAttachService;
import org.smartwork.dal.entity.ZGCmAttach;
import org.smartwork.dal.mapper.ZGCmAttachMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGCmAttachServiceImpl extends ServiceImpl<ZGCmAttachMapper, ZGCmAttach> implements IZGCmAttachService {
}