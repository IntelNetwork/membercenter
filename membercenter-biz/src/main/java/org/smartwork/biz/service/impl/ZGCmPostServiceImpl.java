package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGCmPostService;
import org.smartwork.comm.model.ZGCmPostDto;
import org.smartwork.comm.model.ZGCompanyDto;
import org.smartwork.dal.entity.ZGCmPost;
import org.smartwork.dal.entity.ZGCompany;
import org.smartwork.dal.mapper.ZGCmPostMapper;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZGCmPostServiceImpl extends ServiceImpl<ZGCmPostMapper, ZGCmPost> implements IZGCmPostService {

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
    @Transactional
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
    @Transactional
    @Override
    public void updatePost(ZGCmPostDto zgCmPostDto) {
        //修改岗位
        ZGCmPost zgCmPost = new ZGCmPost();
        BeanCopier.create(ZGCmPostDto.class, ZGCmPost.class, false)
                .copy(zgCmPostDto, zgCmPost, null);
        baseMapper.updateById(zgCmPost);
    }
}