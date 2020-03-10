package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.vo.Result;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.dal.entity.ZGTaskBid;

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

    /***
     * selectionTaskBid方法概述:需求方选标
     * @param taskBidDto
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void selectionTaskBid(ZGTaskBidDto taskBidDto);
}