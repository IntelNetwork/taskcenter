package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.forbes.comm.vo.Result;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.comm.vo.ZGTaskBidVo;
import org.smartwork.comm.vo.ZGTaskVo;
import org.smartwork.dal.entity.ZGTaskBid;

import java.util.List;

public interface IZGTaskBidService extends IService<ZGTaskBid> {

    /***
     * Bidding方法概述:立即竞标
     * @param taskBidDto
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void Bidding(ZGTaskBidDto taskBidDto);


   /**
    * @description 选标修改其它未中标人状态
    * @author xfx
    * @date 2020/3/11 14:37
    * @parameter
    * @return 
    */
    void TaskBiding(long taskId);


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
    ZGTaskBid selectByTaskId(Long taskId);


    /**
     * @description 已参与任务竞标返回视图
     * @author xfx
     * @date 2020/3/13 17:56
     * @parameter
     * @return 
     */
    List<ZGTaskBidVo> taskBidlist(List<ZGTaskBidVo> zgTaskBidVos);
}