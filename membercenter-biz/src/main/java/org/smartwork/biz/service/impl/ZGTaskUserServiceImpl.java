package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGTaskUserService;
import org.smartwork.dal.entity.ZGTaskUser;
import org.smartwork.dal.mapper.ZGTaskUserMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGTaskUserServiceImpl extends ServiceImpl<ZGTaskUserMapper, ZGTaskUser> implements IZGTaskUserService {
}