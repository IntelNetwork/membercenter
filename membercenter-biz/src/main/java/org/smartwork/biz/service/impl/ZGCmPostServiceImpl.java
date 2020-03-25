package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.exception.ForbesException;
import org.smartwork.biz.service.IZGCmPostService;
import org.smartwork.biz.service.IZGCmRelUserService;
import org.smartwork.comm.constant.CmRelUserCommonConstant;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.comm.model.ZGCmPostDto;
import org.smartwork.dal.entity.ZGCmPost;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.mapper.ZGCmPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@Service
public class ZGCmPostServiceImpl extends ServiceImpl<ZGCmPostMapper, ZGCmPost> implements IZGCmPostService {

    @Autowired
    IZGCmRelUserService cmRelUserService;

    /***
     * addPost方法概述:
     * @param zgCmPostDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmPostDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 16:03
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPost(ZGCmPostDto zgCmPostDto) {
        //添加岗位
        ZGCmPost zgCmPost = new ZGCmPost();
        BeanCopier.create(ZGCmPostDto.class, ZGCmPost.class, false)
                .copy(zgCmPostDto, zgCmPost, null);
        baseMapper.insert(zgCmPost);
    }

    /***
     * updatePost方法概述:修改岗位
     * @param zgCmPostDto
     * @return
     * @创建人 Tom
     * @创建时间 2020/3/16 18:08
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePost(ZGCmPostDto zgCmPostDto) {
        //修改岗位
        ZGCmPost zgCmPost = new ZGCmPost();
        BeanCopier.create(ZGCmPostDto.class, ZGCmPost.class, false)
                .copy(zgCmPostDto, zgCmPost, null);
        baseMapper.updateById(zgCmPost);
    }


    /***
     * 概述:公司删除岗位
     * @param idList
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/25
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        //判断中间表中是否有员工拥有此岗位
        idList.forEach(id -> {
            Integer count = cmRelUserService.count(new QueryWrapper<ZGCmRelUser>().eq(CmRelUserCommonConstant.POST_ID, id));
            if (count > 0) {
                throw new ForbesException(MemberBizResultEnum.NO_DELECT_POST.getBizCode()
                        , String.format(MemberBizResultEnum.NO_DELECT_POST.getBizMessage()));
            }
            baseMapper.deleteById(id);
        });
        return false;
    }
}