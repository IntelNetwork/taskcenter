package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.smartwork.comm.model.ZGTaskOrderDto;
import org.smartwork.dal.entity.ZGTaskOrder;

public interface IZGTaskOrderService extends IService<ZGTaskOrder> {

    /***
     * saveOrder方法概述:指定服务方生成订单
     * @param zgTaskOrderDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/10
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void saveOrder(ZGTaskOrderDto zgTaskOrderDto);
}