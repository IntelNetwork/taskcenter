package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskOrderStateEnum;
import org.smartwork.comm.enums.TaskPayStateEnum;
import org.smartwork.comm.model.ZGTaskDto;
import org.smartwork.comm.model.ZGTaskOrderDto;
import org.smartwork.dal.entity.ZGTaskOrder;
import org.smartwork.dal.mapper.ZGTaskOrderMapper;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ZGTaskOrderServiceImpl extends ServiceImpl<ZGTaskOrderMapper, ZGTaskOrder> implements IZGTaskOrderService {


    /***
     * saveOrder方法概述:指定服务方生成订单
     * @param zgTaskOrderDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/10
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrder(ZGTaskDto taskDto) {
        //临时自定义提点比例
        BigDecimal proportion = BigDecimal.valueOf(0.02);
        //提点金额计算
        BigDecimal point = taskDto.getStartPrice().multiply(proportion);
        //托管金额计算
        BigDecimal host = point.add(taskDto.getStartPrice());
        //实际收款计算
        BigDecimal actual = taskDto.getStartPrice();
        //托管金额
        taskDto.getZgTaskOrderDto().setHostAmount(host);
        //实际金额
        taskDto.getZgTaskOrderDto().setActualAmount(actual);
        //提点金额
        taskDto.getZgTaskOrderDto().setPointAmount(point);

        ZGTaskOrder zgTaskOrder = new ZGTaskOrder();
        BeanCopier.create(ZGTaskOrderDto.class, ZGTaskOrder.class, false)
                .copy(taskDto.getZgTaskOrderDto(), zgTaskOrder, null);
        if(taskDto.getZgTaskOrderDto().getHostAmount().intValue()>0 && taskDto.getZgTaskOrderDto().getPointAmount().intValue()>0 && taskDto.getZgTaskOrderDto().getActualAmount().intValue()>0){
            baseMapper.insert(zgTaskOrder);
        }else {
            throw new ForbesException(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizCode()
                    ,String.format(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizMessage()));
        }
    }

    /***
     * updateOrderState方法概述:
     * @param zgTaskOrder
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 10:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    @Override
    public Result<ZGTaskOrder> updateOrderState(ZGTaskOrder zgTaskOrder) {
        Result<ZGTaskOrder> result = new Result<ZGTaskOrder>();
        zgTaskOrder.setOrderStatus(TaskOrderStateEnum.FUND_TRUSTEESHIP.getCode());
        zgTaskOrder.setPayStatus(TaskPayStateEnum.PAID.getCode());
        baseMapper.updateById(zgTaskOrder);
        return result;
    }


}