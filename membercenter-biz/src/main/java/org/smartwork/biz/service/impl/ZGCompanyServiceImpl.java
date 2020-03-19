package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGCompanyService;
import org.smartwork.comm.constant.CompanyConstant;
import org.smartwork.comm.model.ZGCmAttachDto;
import org.smartwork.comm.model.ZGCmRelUserDto;
import org.smartwork.comm.model.ZGCompanyDto;
import org.smartwork.dal.entity.ZGCmAttach;
import org.smartwork.dal.entity.ZGCmRelUser;
import org.smartwork.dal.entity.ZGCompany;
import org.smartwork.dal.mapper.ZGCmAttachMapper;
import org.smartwork.dal.mapper.ZGCmRelUserMapper;
import org.smartwork.dal.mapper.ZGCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZGCompanyServiceImpl extends ServiceImpl<ZGCompanyMapper, ZGCompany> implements IZGCompanyService {

    @Autowired
    ZGCmAttachMapper zgCmAttachMapper;
    @Autowired
    ZGCmRelUserMapper zgCmRelUserMapper;

    /***
     * addCompany方法概述:创建公司
     * @param zgCompanyDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCompanyDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addCompany(ZGCompanyDto zgCompanyDto) {
        //添加公司信息
        ZGCompany zgCompany = new ZGCompany();
        BeanCopier.create(ZGCompanyDto.class, ZGCompany.class, false)
                .copy(zgCompanyDto, zgCompany, null);
        baseMapper.insert(zgCompany);

        Long companyId=zgCompany.getId();
        //添加公司附件信息
        List<ZGCmAttachDto> zgCmAttachDtos=zgCompanyDto.getZgCmAttachDtos();
        if (ConvertUtils.isNotEmpty(zgCmAttachDtos)) {
            zgCmAttachDtos.stream().forEach(zgCmAttachDto -> {
                ZGCmAttach attach = new ZGCmAttach();
                attach.setDataId(companyId);
                attach.setCnName(zgCmAttachDto.getCnName());
                attach.setSuffix(zgCmAttachDto.getSuffix());
                attach.setFilePath(zgCmAttachDto.getFilePath());
                attach.setDataType(zgCmAttachDto.getDataType());
                attach.setOrders(zgCmAttachDto.getOrders());
                zgCmAttachMapper.insert(attach);
            });
        }
        //添加公司用户中间表信息
        ZGCmRelUserDto zgCmRelUserDto=zgCompanyDto.getZgCmRelUserDto();
        if (ConvertUtils.isNotEmpty(zgCmRelUserDto)) {
            ZGCmRelUser zgCmRelUser = new ZGCmRelUser();
            BeanCopier.create(ZGCmRelUserDto.class, ZGCmRelUser.class, false)
                    .copy(zgCmRelUserDto, zgCmRelUser, null);
            zgCmRelUser.setCmId(companyId);
//            //获取当前用户名
//            SysUser user = org.forbes.comm.constant.UserContext.getSysUser();
//            zgCmRelUser.setUserId(user.getId());
//            zgCmRelUser.setUserName(user.getUsername());
            zgCmRelUserMapper.insert(zgCmRelUser);
        }
    }

    /***
     * updateCompany方法概述:公司信息修改
     * @param zgCompanyDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGCompanyDto>
     * @创建人 Tom
     * @创建时间 2020/3/16 14:43
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCompany(ZGCompanyDto zgCompanyDto) {
        //修改公司信息
        ZGCompany zgCompany = new ZGCompany();
        BeanCopier.create(ZGCompanyDto.class, ZGCompany.class, false)
                .copy(zgCompanyDto, zgCompany, null);
        baseMapper.updateById(zgCompany);

        zgCmAttachMapper.delete(new QueryWrapper<ZGCmAttach>().eq(CompanyConstant.DATAID, zgCompanyDto.getId()));
        Long companyId=zgCompanyDto.getId();
        //添加公司附件信息
        List<ZGCmAttachDto> zgCmAttachDtos=zgCompanyDto.getZgCmAttachDtos();
        if (ConvertUtils.isNotEmpty(zgCmAttachDtos)) {
            ZGCmAttach zgCmAttach = new ZGCmAttach();
            zgCmAttachDtos.stream().forEach(zgCmAttachDto -> {
                zgCmAttach.setDataId(companyId);
                zgCmAttach.setCnName(zgCmAttachDto.getCnName());
                zgCmAttach.setSuffix(zgCmAttachDto.getSuffix());
                zgCmAttach.setFilePath(zgCmAttachDto.getFilePath());
                zgCmAttach.setDataType(zgCmAttachDto.getDataType());
                zgCmAttach.setOrders(zgCmAttachDto.getOrders());
                zgCmAttachMapper.insert(zgCmAttach);
            });
        }
    }
}