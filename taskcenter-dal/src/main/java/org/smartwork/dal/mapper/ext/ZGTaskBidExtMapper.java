package org.smartwork.dal.mapper.ext;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.comm.vo.ZGBidTaskVo;
import org.smartwork.comm.vo.ZGTaskBidVo;
import org.smartwork.comm.vo.ZGTaskVo;
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

    /***
     * selectByTaskId方法概述:根据任务id查询已中标会员
     * @param taskId
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskBid>
     * @创建人 Tom
     * @创建时间 2020/3/16 9:57
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    ZGTaskBid selectByTaskId(@Param(value = "taskId") Long taskId);

    IPage<ZGBidTaskVo> getBidding(IPage<ZGBidTaskVo> page, @Param("memberId")Long memberId);
}