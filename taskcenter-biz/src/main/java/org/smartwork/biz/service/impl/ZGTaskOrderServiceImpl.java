package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.comm.constant.CommonConstant;
import org.smartwork.comm.constant.DataColumnConstant;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskOrderStateEnum;
import org.smartwork.comm.enums.TaskPayStateEnum;
import org.smartwork.comm.model.ZGTaskDto;
import org.smartwork.comm.model.ZGTaskOrderDto;
import org.smartwork.dal.entity.ZGTaskBid;
import org.smartwork.dal.entity.ZGTaskOrder;
import org.smartwork.dal.mapper.ZGTaskBidMapper;
import org.smartwork.dal.mapper.ZGTaskOrderMapper;
import org.smartwork.dal.mapper.ext.ZGTaskBidExtMapper;
import org.smartwork.dal.mapper.ext.ZGTaskOrderExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Service
public class ZGTaskOrderServiceImpl extends ServiceImpl<ZGTaskOrderMapper, ZGTaskOrder> implements IZGTaskOrderService {

    @Autowired
    ZGTaskOrderExtMapper zgTaskOrderExtMapper;
    @Autowired
    ZGTaskBidExtMapper zgTaskBidExtMapper;

    /***
     * saveOrder方法概述:指定服务方生成订单
     * @param taskDto
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

        //订单实际收款计算
        BigDecimal actual = taskDto.getZgTaskOrderDto().getActualAmount();
        //提点金额计算
        BigDecimal point = actual.multiply(proportion);
        //托管金额计算
        BigDecimal host = point.add(actual);
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

    /***
     * selectOrder方法概述:根据任务id和会员id查询订单详情
     * @param
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 10:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Override
    public ZGTaskOrder selectOrder(Long taskId, Long memberId) {
        return zgTaskOrderExtMapper.selectOrder(taskId,memberId);
    }

    @Transactional
    @Override
    public void addOrder(ZGTaskOrderDto zgTaskOrderDto) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.ORDER_PREFIX);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat(CommonConstant.YEAR_MONTH_FORMAT);
            zgTaskOrderDto.setSn(dateFormat.format(System.currentTimeMillis()) + dateFormat2.format(System.currentTimeMillis()));
            zgTaskOrderDto.setOrderStatus(TaskOrderStateEnum.UN_MANAGED.getCode());
            zgTaskOrderDto.setPayStatus(TaskPayStateEnum.UN_PAY.getCode());
            //查询任务会员
            ZGTaskBid zgTaskBid = zgTaskBidExtMapper.selectByTaskId(zgTaskOrderDto.getTaskId());
            zgTaskOrderDto.setTaskMemberId(zgTaskBid.getMemberId());
            zgTaskOrderDto.setTaskMemberName(zgTaskBid.getMembeName());

            //临时自定义提点比例
            BigDecimal proportion = BigDecimal.valueOf(0.02);
            //提点金额计算
            BigDecimal point = zgTaskOrderDto.getActualAmount().multiply(proportion);
            //托管金额计算
            BigDecimal host = point.add(zgTaskOrderDto.getActualAmount());
            //托管金额
            zgTaskOrderDto.setHostAmount(host);
            //提点金额
            zgTaskOrderDto.setPointAmount(point);

            ZGTaskOrder zgTaskOrder = new ZGTaskOrder();
            BeanCopier.create(ZGTaskOrderDto.class, ZGTaskOrder.class, false)
                .copy(zgTaskOrderDto, zgTaskOrder, null);
        if(zgTaskOrderDto.getHostAmount().intValue()>0 && zgTaskOrderDto.getPointAmount().intValue()>0 && zgTaskOrderDto.getActualAmount().intValue()>0){
            baseMapper.insert(zgTaskOrder);
        }else {
            throw new ForbesException(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizCode()
                    ,String.format(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizMessage()));
        }
    }


}