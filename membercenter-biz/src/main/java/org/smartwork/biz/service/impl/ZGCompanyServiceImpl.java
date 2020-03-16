package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.dal.entity.ZGCompany;
import org.smartwork.dal.mapper.ZGCompanyMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGCompanyServiceImpl extends ServiceImpl<ZGCompanyMapper, ZGCompany> implements IZGCompanyService {
}