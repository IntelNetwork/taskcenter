package org.smartwork.dal.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.smartwork.dal.entity.ZGTaskOrder;

public interface ZGTaskOrderExtMapper extends BaseMapper<ZGTaskOrder> {

    /***
     * selectOrder方法概述:根据任务id和会员id查询订单详情
     * @param
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 10:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    ZGTaskOrder selectOrder(@Param("taskId")Long taskId, @Param("memberId")Long memberId);
}