package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.SysUserVo;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.comm.constant.CommonConstant;
import org.smartwork.comm.constant.SaveValid;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.constant.UserContext;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskOrderStateEnum;
import org.smartwork.comm.enums.TaskPayStateEnum;
import org.smartwork.comm.model.ZGTaskOrderDto;
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
     * @param zgTaskOrderDto
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTaskOrder>
     * @创建人 Tom
     * @创建时间 2020/3/5 9:32
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("任务订单生成")
    public Result<ZGTaskOrderDto> addOrder(@RequestBody @Validated(value=SaveValid.class) ZGTaskOrderDto zgTaskOrderDto){
        Result<ZGTaskOrderDto> result=new Result<ZGTaskOrderDto>();
        try {
            izgTaskOrderService.addOrder(zgTaskOrderDto);
            result.setResult(zgTaskOrderDto);
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
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
    public Result<ZGTaskOrder> selectOrder(@RequestParam(value = "taskId", required = true) Long taskId) {
        Result<ZGTaskOrder> result = new Result<ZGTaskOrder>();
        //加入需求方(当前登录用户)ID
        SysUserVo user = UserContext.getSysUser();
        Long memberId=user.getId();
        ZGTaskOrder zgTaskOrder=izgTaskOrderService.selectOrder(taskId,memberId);
        result.setResult(zgTaskOrder);
        return result;
    }

    /***
     * 概述:支付后修改任务和订单状态
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/modify-order-status", method = RequestMethod.PUT)
    @ApiOperation("支付后修改任务和订单状态")
    public Result<ZGTaskOrder> modifyOrderStatus(String sn){
    Result<ZGTaskOrder> result = new Result<>();
    izgTaskOrderService.modifyOrderStatus(sn);
    return result;
    }

}
