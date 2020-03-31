package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGLevelPermMemberService;
import org.smartwork.comm.model.ZGLevelPermMemberDto;
import org.smartwork.dal.entity.ZGLevelPermMember;
import org.smartwork.dal.mapper.ZGLevelPermMemberMapper;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZGLevelPermMemberServiceImpl extends ServiceImpl<ZGLevelPermMemberMapper, ZGLevelPermMember> implements IZGLevelPermMemberService {

    /***
     * addPermMember方法概述:分配会员权限
     * @param zgLevelPermMemberDtos
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGLevelPermMemberDto>
     * @创建人 Tom
     * @创建时间 2020/3/31 9:25
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addPermMember(Long memberId, List<ZGLevelPermMemberDto> zgLevelPermMemberDtos) {
        baseMapper.delete(new QueryWrapper<ZGLevelPermMember>().eq("member_id",memberId));

        //添加会员权限
        if(ConvertUtils.isNotEmpty(zgLevelPermMemberDtos)){
            zgLevelPermMemberDtos.stream().forEach(zgLevelPermMemberDto -> {
                ZGLevelPermMember zgLevelPermMember=new ZGLevelPermMember();
                zgLevelPermMember.setMemberId(memberId);
                zgLevelPermMember.setPermId(zgLevelPermMemberDto.getPermId());
                zgLevelPermMember.setPermCode(zgLevelPermMemberDto.getPermCode());
                baseMapper.insert(zgLevelPermMember);
            });

        }
    }

}