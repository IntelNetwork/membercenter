package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGCmPostService;
import org.smartwork.dal.entity.ZGCmPost;
import org.smartwork.dal.mapper.ZGCmPostMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGCmPostServiceImpl extends ServiceImpl<ZGCmPostMapper, ZGCmPost> implements IZGCmPostService {
}