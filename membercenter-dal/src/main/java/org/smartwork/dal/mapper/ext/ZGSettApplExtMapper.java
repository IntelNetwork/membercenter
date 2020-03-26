package org.smartwork.dal.mapper.ext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.smartwork.dal.entity.ZGSettAppl;
public interface ZGSettApplExtMapper {


    /***
     * 分页查询待转账订单
     * @param page
     * @param reviewStatus
     * @param settlStatus
     * @return
     */
    IPage<ZGSettAppl> pageTransfer(IPage<ZGSettAppl> page, @Param("reviewStatus")String reviewStatus, @Param("settlStatus")String settlStatus);
}
