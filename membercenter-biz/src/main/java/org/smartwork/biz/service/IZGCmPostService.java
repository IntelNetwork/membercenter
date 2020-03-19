package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.smartwork.comm.model.ZGCmPostDto;
import org.smartwork.dal.entity.ZGCmPost;

public interface IZGCmPostService extends IService<ZGCmPost> {

    /***
     * addPost方法概述:
     * @param zgCmPostDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmPostDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 16:03
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addPost(ZGCmPostDto zgCmPostDto);

    /***
     * updatePost方法概述:修改岗位
     * @param zgCmPostDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCmPostDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 18:08
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void updatePost(ZGCmPostDto zgCmPostDto);
}