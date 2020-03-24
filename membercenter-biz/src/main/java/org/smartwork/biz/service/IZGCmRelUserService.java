package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.comm.vo.ZGCmRelUserVo;
import org.smartwork.dal.entity.ZGCmRelUser;

public interface IZGCmRelUserService extends IService<ZGCmRelUser> {
    /***
     * addCmUser方法概述:
     * @param zgCmRelUserDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmRelUserDto>
     * @创建人 Tom
     * @创建时间 2020/3/17 11:50
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addCmUser(ZGCmRelUserDto zgCmRelUserDto);

    /***
     * updateCmUser方法概述:员工岗位变更
     * @param zgCmRelUserDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmRelUserDto>
     * @创建人 Tom
     * @创建时间 2020/3/17 13:59
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void updateCmUser(ZGCmRelUserDto zgCmRelUserDto);


    /***
     * cmUserDetail方法概述:公司查看员工详情
     * @param cmId,userName
     * @创建人 frunk
     * @创建时间 2020/3/17 13:59
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
     ZGCmRelUserVo cmUserDetail(Long cmId, String userName);
}