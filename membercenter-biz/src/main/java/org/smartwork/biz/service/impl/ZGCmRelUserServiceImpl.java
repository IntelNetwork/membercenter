package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.comm.model.ZGCmPostDto;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.dal.entity.ZGCmPost;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.mapper.ZGCmRelUserMapper;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZGCmRelUserServiceImpl extends ServiceImpl<ZGCmRelUserMapper, ZGCmRelUser> implements IZGCmRelUserService {

    /***
     * addCmUser方法概述:
     * @param zgCmRelUserDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmRelUserDto>
     * @创建人 Tom
     * @创建时间 2020/3/17 11:50
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addCmUser(ZGCmRelUserDto zgCmRelUserDto) {
        ZGCmRelUser zgCmRelUser = new ZGCmRelUser();
        BeanCopier.create(ZGCmRelUserDto.class, ZGCmRelUser.class, false)
                .copy(zgCmRelUserDto, zgCmRelUser, null);
        baseMapper.insert(zgCmRelUser);
    }

    /***
     * updateCmUser方法概述:员工岗位变更
     * @param zgCmRelUserDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmRelUserDto>
     * @创建人 Tom
     * @创建时间 2020/3/17 13:59
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCmUser(ZGCmRelUserDto zgCmRelUserDto) {
        ZGCmRelUser zgCmRelUser = new ZGCmRelUser();
        BeanCopier.create(ZGCmRelUserDto.class, ZGCmRelUser.class, false)
                .copy(zgCmRelUserDto, zgCmRelUser, null);
        baseMapper.updateById(zgCmRelUser);
    }
}