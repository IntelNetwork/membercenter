package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.vo.Result;
import org.smartwork.comm.model.ZGTeamRelUserDto;
import org.smartwork.comm.vo.ZGTeamRelUserVo;
import org.smartwork.dal.entity.ZGTeamRelUser;

import java.util.List;

public interface IZGTeamRelUserService extends IService<ZGTeamRelUser> {
    /***
     * addTeamRelUser方法概述:团队任务分配
     * @param teamRelUserDtos
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addTeamRelUser(List<ZGTeamRelUserDto> teamRelUserDtos);


    /***
     * teamUserDetail方法概述:团队查看员工详情
     * @param teamId,userName
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/23
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    ZGTeamRelUserVo teamUserDetail(Long teamId, String userName);

}