package controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.BizResultEnum;
import org.smartwork.comm.enums.TaskStateEnum;
import org.smartwork.comm.enums.YesNoEnum;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.vo.Result;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.entity.ZGTaskBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 * 类概述:任务竞标记录API控制层
 * @创建人 niehy(Frunk)
 * @创建时间 2020/3/2
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/api-bid")
@Api(tags={"任务竞标记录API控制层"})
@Slf4j
public class ZGTaskBidApiController {


    @Autowired
    IZGTaskBidService taskBidService;


    /***
     * 方法概述:立即竞标
     * @param taskBid 竞标实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/bidding",method = RequestMethod.PUT)
    @ApiOperation("立即竞标")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_RELEASE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_RELEASE)
    })
    public Result<ZGTaskBid> updateTaskBid(@RequestBody @Validated(value = UpdateValid.class)ZGTaskBid taskBid){
        log.debug("传入参数为:" + JSON.toJSONString(taskBid));
        Result<ZGTaskBid> result = new Result<ZGTaskBid>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(taskBid)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //更改状态 未中标
        taskBid.setHitState(YesNoEnum.NO.getCode());
        //进入业务类继续操作
        taskBidService.Bidding(taskBid);
        result.setResult(taskBid);
        log.debug("返回内容为:" + JSON.toJSONString(taskBid));
        return result;
    }
}
