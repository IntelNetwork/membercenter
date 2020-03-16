package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGProjectPerService;
import org.smartwork.dal.entity.ZGProjectPer;
import org.smartwork.dal.mapper.ZGProjectPerMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGProjectPerServiceImpl extends ServiceImpl<ZGProjectPerMapper, ZGProjectPer> implements IZGProjectPerService {
}