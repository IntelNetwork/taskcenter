package org.smartwork.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.vo.Result;
import org.smartwork.comm.model.ZGTaskDto;
import org.smartwork.comm.model.ZGTaskOrderDto;
import org.smartwork.dal.entity.ZGTaskOrder;

public interface IZGTaskOrderService extends IService<ZGTaskOrder> {

    /***
     * modifyOrderStatus方法概述:支付成功后修改状态
     * @param  sn
     * @创建人 niehy(Frunk)
     * @创建时间 2020/2/29
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void modifyOrderStatus(String sn);


    /***
     * selectOrder方法概述:根据任务id和会员id查询订单详情
     * @param
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 10:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    ZGTaskOrder selectOrder(Long taskId,Long memberId);


    /***
     * addOrder方法概述:生成订单
     * @创建人 Tom
     * @创建时间 2020/3/5 10:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addOrder(ZGTaskOrderDto zgTaskOrderDto);

    /***
     * addOrder方法概述:指定服务方生成订单
     * @创建人 nhy
     * @创建时间 2020/3/5 10:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    void addOrderPro(ZGTaskOrderDto zgTaskOrderDto);
}