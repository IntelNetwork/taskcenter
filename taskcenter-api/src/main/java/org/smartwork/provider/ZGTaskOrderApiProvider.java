package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.comm.constant.SaveValid;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskOrderStateEnum;
import org.smartwork.comm.enums.TaskPayStateEnum;
import org.smartwork.dal.entity.ZGTaskOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        zgTaskOrder.setOrderStatus(TaskOrderStateEnum.UN_MANAGED.getCode());
        zgTaskOrder.setPayStatus(TaskPayStateEnum.UN_PAY.getCode());
        izgTaskOrderService.save(zgTaskOrder);
        result.setResult(zgTaskOrder);
        return result;
    }

    /***
     * updateOrderState方法概述:
     * @param sn, orderStatus
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 10:04
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-order-state", method = RequestMethod.PUT)
    @ApiOperation("订单状态修改")
    public Result<ZGTaskOrder> updateOrderState(@RequestParam(value="sn",required=true)String sn, @RequestParam(value = "orderStatus",required = true)String orderStatus){
        Result<ZGTaskOrder> result=new Result<ZGTaskOrder>();
        ZGTaskOrder zgTaskOrder = izgTaskOrderService.getById(sn);
        if(ConvertUtils.isEmpty(zgTaskOrder)){
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        boolean isOrderStatus = TaskOrderStateEnum.existsTaskOrderStateEnum(orderStatus);
        if(!isOrderStatus){
            result.setBizCode(TaskBizResultEnum.ORDER_STATUS_NO_EXISTS.getBizCode());
            result.setMessage(String.format(TaskBizResultEnum.ORDER_STATUS_NO_EXISTS.getBizFormateMessage(), orderStatus));
            return result;
        }
        zgTaskOrder.setOrderStatus(orderStatus);
        izgTaskOrderService.updateById(zgTaskOrder);
        return result;
    }

    /***
     * updatePayState方法概述:
     * @param sn, payStatus
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 10:15
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-pay-state", method = RequestMethod.PUT)
    @ApiOperation("支付状态修改")
    public Result<ZGTaskOrder> updatePayState(@RequestParam(value="sn",required=true)String sn, @RequestParam(value = "payStatus",required = true)String payStatus){
        Result<ZGTaskOrder> result=new Result<ZGTaskOrder>();
        ZGTaskOrder zgTaskOrder = izgTaskOrderService.getById(sn);
        if(ConvertUtils.isEmpty(zgTaskOrder)){
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        boolean isPayStatus = TaskPayStateEnum.existsTaskPayStateEnum(payStatus);
        if(!isPayStatus){
            result.setBizCode(TaskBizResultEnum.PAY_STATUS_NO_EXISTS.getBizCode());
            result.setMessage(String.format(TaskBizResultEnum.PAY_STATUS_NO_EXISTS.getBizFormateMessage(), payStatus));
            return result;
        }
        zgTaskOrder.setPayStatus(payStatus);
        izgTaskOrderService.updateById(zgTaskOrder);
        return result;
    }



}
