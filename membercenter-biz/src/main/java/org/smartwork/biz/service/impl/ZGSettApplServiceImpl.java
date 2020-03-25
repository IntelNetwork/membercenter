package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.dal.entity.ZGSettAppl;
import org.smartwork.dal.mapper.ZGSettApplMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGSettApplServiceImpl extends ServiceImpl<ZGSettApplMapper, ZGSettAppl> implements IZGSettApplService {
}