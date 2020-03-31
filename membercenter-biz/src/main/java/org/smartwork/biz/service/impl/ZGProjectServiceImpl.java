package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGProjectService;
import org.smartwork.comm.enums.ProjectTypeEnum;
import org.smartwork.comm.model.ZGProAttachDto;
import org.smartwork.comm.model.ZGProjectDto;
import org.smartwork.dal.entity.*;
import org.smartwork.dal.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class ZGProjectServiceImpl extends ServiceImpl<ZGProjectMapper, ZGProject> implements IZGProjectService {

    @Autowired
    ZGProAttachMapper proAttachMapper;
    @Autowired
    ZGProTaskMapper proTaskMapper;
    @Autowired
    ZGProjectMapper projectMapper;
    @Autowired
    ZGTeamMapper teamMapper;
    @Autowired
    ZGCompanyMapper companyMapper;


    /***
     * createPro方法概述:创建项目
     * @param projectDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createPro(ZGProjectDto projectDto) {
        ZGProject project = new ZGProject();
        BeanCopier.create(ZGProjectDto.class, ZGProject.class, false)
                .copy(projectDto, project, null);
        baseMapper.insert(project);

        //关联创建附件
        List<ZGProAttachDto> zgProAttachDtos = projectDto.getProAttachDtos();
        if (ConvertUtils.isNotEmpty(zgProAttachDtos)) {
            Long dataId = project.getId();
            ZGProAttach attach = new ZGProAttach();
            zgProAttachDtos.stream().forEach(temp -> {
                attach.setDataId(dataId);
                attach.setCnName(temp.getCnName());
                attach.setFilePath(temp.getFilePath());
                attach.setSuffix(temp.getSuffix());
                attach.setDataType(temp.getDataType());
                attach.setOrders(temp.getOrders());
                //执行添加
                proAttachMapper.insert(attach);
            });
        }
    }


    /***
     * modifyPro方法概述:编辑项目
     * @param projectDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/24
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyPro(ZGProjectDto projectDto) {
        ZGProject project = new ZGProject();
        BeanCopier.create(ZGProjectDto.class, ZGProject.class, false)
                .copy(projectDto, project, null);
        baseMapper.updateById(project);

        //关联添加附件
        List<ZGProAttachDto> zgProAttachDtos = projectDto.getProAttachDtos();
        if (ConvertUtils.isNotEmpty(zgProAttachDtos)) {
            Long dataId = project.getId();
            ZGProAttach attach = new ZGProAttach();
            zgProAttachDtos.stream().forEach(temp -> {
                attach.setDataId(dataId);
                attach.setCnName(temp.getCnName());
                attach.setFilePath(temp.getFilePath());
                attach.setSuffix(temp.getSuffix());
                attach.setDataType(temp.getDataType());
                attach.setOrders(temp.getOrders());
                //执行添加
                proAttachMapper.insert(attach);
            });
        }
    }


    /***
     * modifyPro方法概述:删除项目同时删除子项目
     * @param id
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/31
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(ZGProject project) {
        SysUser user = UserContext.getSysUser();
        //是当前团队的项目,可以删除
        if (project.getDataType().equals(ProjectTypeEnum.TEAM_PRO)) {
            ZGTeam team = teamMapper.selectOne(new QueryWrapper<ZGTeam>().eq("legal_person", user.getRealname()));
            if (ConvertUtils.isNotEmpty(team)) {
                projectMapper.deleteById(project);
                proTaskMapper.deleteById(new QueryWrapper<ZGProTask>().eq("pro_id", project.getId()));
            }
        }
        //是当前公司的项目,可以删除
        if (project.getDataType().equals(ProjectTypeEnum.COMPANY_PRO)) {
            ZGCompany company = companyMapper.selectOne(new QueryWrapper<ZGCompany>().eq("legal_person", user.getRealname()));
            if (ConvertUtils.isNotEmpty(company)) {
                projectMapper.deleteById(project);
                proTaskMapper.deleteById(new QueryWrapper<ZGProTask>().eq("pro_id", project.getId()));
            }
        }

        return true;
    }
}