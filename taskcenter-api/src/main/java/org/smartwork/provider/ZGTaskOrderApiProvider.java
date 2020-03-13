package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.comm.constant.CommonConstant;
import org.smartwork.comm.constant.SaveValid;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskOrderStateEnum;
import org.smartwork.comm.enums.TaskPayStateEnum;
import org.smartwork.comm.vo.ZGTaskVo;
import org.smartwork.dal.entity.ZGTaskOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author lzw
 * @date 2020/3/5 9:13
 */
@RestController
@RequestMapping("/${smartwork.verision}/order")
@Api(tags={"任务订单API控制层"})
@Slf4j
public class ZGTaskOrderApiProvider {

    @Autowired
    IZGTaskOrderService izgTaskOrderService;

    /***
     * addProductLabel方法概述:
     * @param zgTaskOrder
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 9:32
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("任务订单生成")
    public Result<ZGTaskOrder> addProductLabel(@RequestBody @Validated(value=SaveValid.class) ZGTaskOrder zgTaskOrder){
        Result<ZGTaskOrder> result=new Result<ZGTaskOrder>();
        try {
            if(zgTaskOrder.getHostAmount().intValue()>0 && zgTaskOrder.getPointAmount().intValue()>0 && zgTaskOrder.getActualAmount().intValue()>0){
                SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.ORDER_PREFIX);
                SimpleDateFormat dateFormat2 = new SimpleDateFormat(CommonConstant.YEAR_MONTH_FORMAT);
                zgTaskOrder.setSn(dateFormat.format(result.getTimestamp())+dateFormat2.format(result.getTimestamp()));
                zgTaskOrder.setOrderStatus(TaskOrderStateEnum.UN_MANAGED.getCode());
                zgTaskOrder.setPayStatus(TaskPayStateEnum.UN_PAY.getCode());
                //临时自定义提点比例
                BigDecimal proportion = BigDecimal.valueOf(0.02);
                //提点金额计算
                BigDecimal point = zgTaskOrder.getActualAmount().multiply(proportion);
                //托管金额计算
                BigDecimal host = point.add(zgTaskOrder.getActualAmount());
                //托管金额
                zgTaskOrder.setHostAmount(host);
                //提点金额
                zgTaskOrder.setPointAmount(point);
                izgTaskOrderService.save(zgTaskOrder);
                result.setResult(zgTaskOrder);
            }else{
                result.setBizCode(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizCode());
                result.setMessage(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizMessage());
                return result;
            }
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }

    /***
     * updateOrderState方法概述:订单状态修改
     * @param zgTaskOrder
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 10:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-order-state", method = RequestMethod.PUT)
    @ApiOperation("订单状态修改(支付成功后)")
    public Result<ZGTaskOrder> updateOrderState(@RequestBody @Validated(value=UpdateValid.class) ZGTaskOrder zgTaskOrder){
        Result<ZGTaskOrder> result=new Result<ZGTaskOrder>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(zgTaskOrder)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        izgTaskOrderService.updateOrderState(zgTaskOrder);
        result.setResult(zgTaskOrder);
        return result;
    }

    /***
     * selectOrder方法概述:通过会员id和任务id查询订单详情
     * @param taskId, memberId
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/13 13:59
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-order", method = RequestMethod.GET)
    @ApiOperation("通过会员id和任务id查询订单详情")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name="taskId",value = "任务id"),
                    @ApiImplicitParam(name="memberId",value = "会员id")
            }
    )
    public Result<ZGTaskOrder> selectOrder(@RequestParam(value = "taskId", required = true) Long taskId,@RequestParam(value = "memberId", required = true) Long memberId) {
        Result<ZGTaskOrder> result = new Result<ZGTaskOrder>();
        ZGTaskOrder zgTaskOrder=izgTaskOrderService.selectOrder(taskId,memberId);
        result.setResult(zgTaskOrder);
        return result;
    }

}
