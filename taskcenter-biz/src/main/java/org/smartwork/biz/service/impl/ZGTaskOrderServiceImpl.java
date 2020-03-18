package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.cache.UserCache;
import org.forbes.comm.constant.UserContext;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.SysUserVo;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.comm.constant.CommonConstant;
import org.smartwork.comm.constant.TaskColumnConstant;
import org.smartwork.comm.constant.TaskOrderCommonConstant;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskOrderStateEnum;
import org.smartwork.comm.enums.TaskPayStateEnum;
import org.smartwork.comm.enums.TaskStateEnum;
import org.smartwork.comm.model.ZGTaskDto;
import org.smartwork.comm.model.ZGTaskOrderDto;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.entity.ZGTaskBid;
import org.smartwork.dal.entity.ZGTaskOrder;
import org.smartwork.dal.mapper.ZGTaskMapper;
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
    @Autowired
    ZGTaskMapper taskMapper;


    /**
     * 支付后修改任务和订单状态
     *  nhy
     * @param sn
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyOrderStatus(String sn) {
        ZGTaskOrder order = zgTaskOrderExtMapper.selectOne(new QueryWrapper<ZGTaskOrder>().eq(TaskOrderCommonConstant.SN, sn));
        ZGTask task = taskMapper.selectOne(new QueryWrapper<ZGTask>().eq(TaskColumnConstant.ID, order.getTaskId()));

        if(!task.getTaskState().equalsIgnoreCase(TaskStateEnum.PAYMENT_GRATUITY.getCode())){
            //只有在任务状态为6订单支付的时候再能修改
            throw new ForbesException(TaskBizResultEnum.TASK_NOT_PAY.getBizCode()
            , String.format(TaskBizResultEnum.TASK_NOT_PAY.getBizMessage()));
        }

        //修改任务订单状态
        order.setOrderStatus(TaskOrderStateEnum.FUND_TRUSTEESHIP.getCode());
        order.setPayStatus(TaskPayStateEnum.PAID.getCode());
        baseMapper.updateById(order);
        //修改任务状态
        task.setTaskState(TaskStateEnum.TRUST_REWARD.getCode());
        taskMapper.updateById(task);
    }

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
        ZGTaskOrder zgTaskOrder = new ZGTaskOrder();
        BeanCopier.create(ZGTaskOrderDto.class, ZGTaskOrder.class, false)
                .copy(taskDto.getZgTaskOrderDto(), zgTaskOrder, null);
        if (taskDto.getZgTaskOrderDto().getHostAmount().intValue() > 0 && taskDto.getZgTaskOrderDto().getPointAmount().intValue() > 0 && taskDto.getZgTaskOrderDto().getActualAmount().intValue() > 0) {
            //加入需求方(当前登录用户)ID
            SysUser user = org.forbes.comm.constant.UserContext.getSysUser();
            zgTaskOrder.setMemberId(user.getId());
            zgTaskOrder.setMemberName(user.getUsername());
            baseMapper.insert(zgTaskOrder);
        } else {
            throw new ForbesException(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizCode()
                    , String.format(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizMessage()));
        }
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
        return zgTaskOrderExtMapper.selectOrder(taskId, memberId);
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

        //加入需求方ID,用户名
        SysUser user = org.forbes.comm.constant.UserContext.getSysUser();
        zgTaskOrderDto.setMemberName(user.getUsername());
        zgTaskOrderDto.setMemberId(user.getId());
        
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
        if (zgTaskOrderDto.getHostAmount().intValue() > 0 && zgTaskOrderDto.getPointAmount().intValue() > 0 && zgTaskOrderDto.getActualAmount().intValue() > 0) {
            baseMapper.insert(zgTaskOrder);
        } else {
            throw new ForbesException(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizCode()
                    , String.format(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizMessage()));
        }
    }


}