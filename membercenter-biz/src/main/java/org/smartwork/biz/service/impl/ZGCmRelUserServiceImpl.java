package org.smartwork.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.service.ISysUserService;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.comm.constant.CmRelUserCommonConstant;
import org.smartwork.comm.enums.CmAdminFlagEnum;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.comm.vo.ZGCmRelUserVo;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.mapper.ZGCmRelUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZGCmRelUserServiceImpl extends ServiceImpl<ZGCmRelUserMapper, ZGCmRelUser> implements IZGCmRelUserService {

    @Autowired
    ZGCmRelUserMapper cmRelUserMapper;

    @Autowired
    ISysUserService sysUserService;

    /***
     * addCmUser方法概述:添加员工
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
        zgCmRelUser.setAdminFlag(CmAdminFlagEnum.SUPER_ADMIN.getCode());
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


    /***
     * updateCmUser方法概述:公司查看员工详情
     * @param cmId,userName
     * @创建人 frunk
     * @创建时间 2020/3/17 13:59
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public ZGCmRelUserVo cmUserDetail(Long cmId, String userName) {
        QueryWrapper<ZGCmRelUser> qw = new QueryWrapper<>();
        qw.eq(CmRelUserCommonConstant.CM_ID, cmId);
        qw.eq(CmRelUserCommonConstant.CM_USER_NAME, userName);
        ZGCmRelUser cmRelUser = cmRelUserMapper.selectOne(qw);
        Result<SysUser> user = sysUserService.getUserByName(userName);
        ZGCmRelUserVo cmRelUserVo = new ZGCmRelUserVo();

        BeanCopier.create(ZGCmRelUser.class, ZGCmRelUserVo.class, false)
                .copy(cmRelUser, cmRelUserVo, null);
        cmRelUserVo.setUser(user);
        return cmRelUserVo;
    }
}