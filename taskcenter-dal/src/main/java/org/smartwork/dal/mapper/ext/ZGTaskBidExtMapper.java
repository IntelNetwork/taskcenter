package org.smartwork.dal.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.dal.entity.ZGTaskBid;

public interface ZGTaskBidExtMapper extends BaseMapper<ZGTaskBid> {

    /***
     * taskBidDetail方法概述:查看竞标详情
     * @param id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    ZGTaskBidDto taskBidDetail(@Param(value = "id") Long id);
}