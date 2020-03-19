package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import net.bytebuddy.asm.Advice;
import org.forbes.comm.enums.ActivityStateEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGMemberLevelService;
import org.smartwork.comm.enums.MemberBizResultEnum;
import org.smartwork.dal.entity.ZGMemberLevel;
import org.smartwork.dal.entity.ZGMemberLevelPerm;
import org.smartwork.dal.entity.ZGMemberLevelPermEle;
import org.smartwork.dal.mapper.ZGMemberLevelMapper;
import org.smartwork.dal.mapper.ZGMemberLevelPermEleMapper;
import org.smartwork.dal.mapper.ZGMemberLevelPermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class ZGMemberLevelServiceImpl extends ServiceImpl<ZGMemberLevelMapper, ZGMemberLevel> implements IZGMemberLevelService {



    @Autowired
    ZGMemberLevelPermMapper memberLevelPermMapper;
    @Autowired
    ZGMemberLevelPermEleMapper memberLevelPermEleMapper;


    /***
     *
     * @param memberLevel
     * @return
     */
    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean save(ZGMemberLevel  memberLevel) {
        boolean isInsert = SqlHelper.retBool(baseMapper.insert(memberLevel));
        List<ZGMemberLevelPerm> memberLevelPerms = memberLevel.getMemberLevelPerms();
        if (ConvertUtils.isNotEmpty(memberLevelPerms)){
            long levelId =  memberLevel.getId();
            memberLevelPerms.forEach(memberLevelPerm -> {
                memberLevelPerm.setLevelId(levelId);
                memberLevelPermMapper.insert(memberLevelPerm);
                List<ZGMemberLevelPermEle>  memberLevelPermEles = memberLevelPerm.getMemberLevelPermEles();
                if (ConvertUtils.isNotEmpty(memberLevelPermEles)){
                    memberLevelPermEles.forEach(memberLevelPermEle->{
                        memberLevelPermEle.setLevelId(levelId);
                        if(ConvertUtils.isNotEmpty(memberLevelPermEle.getPermCode())){
                            memberLevelPermEle.setPermCode(memberLevelPerm.getPermCode());
                        }
                        memberLevelPermEleMapper.insert(memberLevelPermEle);
                    });
                }
            });
        }
        return isInsert;
    }


    /***
     *
     * @param memberLevel
     * @return
     */
    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean updateById(ZGMemberLevel  memberLevel) {
        boolean isUp =  SqlHelper.retBool(baseMapper.updateById(memberLevel));
        long levelId = memberLevel.getId();
        memberLevelPermMapper.delete(new QueryWrapper<ZGMemberLevelPerm>().eq("level_id",levelId));
        memberLevelPermEleMapper.delete(new QueryWrapper<ZGMemberLevelPermEle>().eq("level_id",levelId));
        List<ZGMemberLevelPerm> memberLevelPerms = memberLevel.getMemberLevelPerms();
        if (ConvertUtils.isNotEmpty(memberLevelPerms)){
            memberLevelPerms.forEach(memberLevelPerm -> {
                memberLevelPerm.setLevelId(levelId);
                memberLevelPermMapper.insert(memberLevelPerm);
                List<ZGMemberLevelPermEle>  memberLevelPermEles = memberLevelPerm.getMemberLevelPermEles();
                if (ConvertUtils.isNotEmpty(memberLevelPermEles)){
                      memberLevelPermEles.forEach(memberLevelPermEle->{
                        memberLevelPermEle.setLevelId(levelId);
                        if(ConvertUtils.isNotEmpty(memberLevelPermEle.getPermCode())){
                            memberLevelPermEle.setPermCode(memberLevelPerm.getPermCode());
                        }
                        memberLevelPermEleMapper.insert(memberLevelPermEle);
                    });
                }
            });
        }
        return isUp;
    }

    /***
     * 重写删除
     * @param id
     * @return
     */
    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean removeById(Serializable id) {
       int stateCount =  baseMapper.selectCount(new QueryWrapper<ZGMemberLevel>()
                .eq("state", ActivityStateEnum.ACTIVITY.getCode())
                .eq("id",id));
       if (stateCount > 0 ){
           throw new ForbesException(MemberBizResultEnum.ACTIVITY_STATE.getBizCode(),MemberBizResultEnum.ACTIVITY_STATE.getBizMessage());
       }
        boolean isDel = SqlHelper.retBool(baseMapper.deleteById(id));
        memberLevelPermMapper.delete(new QueryWrapper<ZGMemberLevelPerm>().eq("level_id",id));
        memberLevelPermEleMapper.delete(new QueryWrapper<ZGMemberLevelPermEle>().eq("level_id",id));
        return isDel;
    }


    /***
     * 重写批量删除
     * @param idList
     * @return
     */
    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        int stateCount =  baseMapper.selectCount(new QueryWrapper<ZGMemberLevel>()
                .eq("state", ActivityStateEnum.ACTIVITY.getCode())
                .in("id",idList));
        if (stateCount > 0 ){
            throw new ForbesException(MemberBizResultEnum.ACTIVITY_STATE.getBizCode(),MemberBizResultEnum.ACTIVITY_STATE.getBizMessage());
        }
        boolean isDels =  CollectionUtils.isEmpty(idList) ? false : SqlHelper.retBool(baseMapper.deleteBatchIds(idList));
        memberLevelPermMapper.delete(new QueryWrapper<ZGMemberLevelPerm>().in("level_id",idList));
        memberLevelPermEleMapper.delete(new QueryWrapper<ZGMemberLevelPermEle>().in("level_id",idList));
        return isDels;
    }


    /***
     *
     * @param id
     * @return
     */
    @Override
    public ZGMemberLevel getById(Serializable id) {
        ZGMemberLevel memberLevel = baseMapper.selectById(id);
        List<ZGMemberLevelPerm> memberLevelPerms = memberLevelPermMapper.selectList(new QueryWrapper<ZGMemberLevelPerm>().eq("level_id",id));
        if(ConvertUtils.isNotEmpty(memberLevelPerms)){
            memberLevel.setMemberLevelPerms(memberLevelPerms);
        }
        List<ZGMemberLevelPermEle> memberLevelPermEles = memberLevelPermEleMapper.selectList(new QueryWrapper<ZGMemberLevelPermEle>().eq("level_id",id));
        if (ConvertUtils.isNotEmpty(memberLevelPermEles)){
            memberLevel.setMemberLevelPermEles(memberLevelPermEles);
        }
        return memberLevel;
    }
}