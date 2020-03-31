package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.smartwork.comm.model.ZGLevelPermMemberDto;
import org.smartwork.dal.entity.ZGLevelPermMember;

import java.util.List;

public interface IZGLevelPermMemberService extends IService<ZGLevelPermMember> {

    /***
     * addPermMember方法概述:分配会员权限
     * @param zgLevelPermMemberDtos
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGLevelPermMemberDto>
     * @创建人 Tom
     * @创建时间 2020/3/31 9:25
     * @修改人 (修改了该文件 ， 请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addPermMember(Long memberId, List<ZGLevelPermMemberDto> zgLevelPermMemberDtos);

}