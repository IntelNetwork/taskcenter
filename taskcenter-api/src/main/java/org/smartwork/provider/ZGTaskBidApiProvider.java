package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.BizResultEnum;
import org.smartwork.comm.enums.YesNoEnum;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.vo.Result;
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
@RequestMapping("/${smartwork.verision}/bid")
@Api(tags = {"任务竞标记录API控制层"})
@Slf4j
public class ZGTaskBidApiProvider {


    @Autowired
    IZGTaskBidService taskBidService;


    /***
     * 方法概述:立即竞标
     * @param taskBidDto 竞标实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/bidding", method = RequestMethod.PUT)
    @ApiOperation("立即竞标")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_RELEASE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_RELEASE)
    })
    public Result<ZGTaskBidDto> updateTaskBid(@RequestBody @Validated(value = UpdateValid.class) ZGTaskBidDto taskBidDto) {
        log.debug("传入参数为:" + JSON.toJSONString(taskBidDto));
        Result<ZGTaskBidDto> result = new Result<ZGTaskBidDto>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(taskBidDto)) {
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //更改状态 未中标(已参与,等待审核)
        taskBidDto.setHitState(YesNoEnum.NO.getCode());
        //进入业务类继续操作
        taskBidService.Bidding(taskBidDto);
        result.setResult(taskBidDto);
        log.debug("返回内容为:" + JSON.toJSONString(taskBidDto));
        return result;
    }


    /***
     * 方法概述:服务方确认竞标结果
     * @param taskBid 竞标实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/confirm-result", method = RequestMethod.PUT)
    @ApiOperation("服务方确认竞标结果")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_CONFIRM_RESULT_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_CONFIRM_RESULT)
    })
    public Result<ZGTaskBid> confirmResult(@RequestBody @Validated(value = UpdateValid.class) ZGTaskBid taskBid) {
        log.debug("传入参数为:" + JSON.toJSONString(taskBid));
        Result<ZGTaskBid> result = new Result<ZGTaskBid>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(taskBid)) {
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //更改状态 已中标(已参与,等待审核)
        taskBid.setHitState(YesNoEnum.YES.getCode());
        //执行确认竞标操作
        taskBidService.updateById(taskBid);
        result.setResult(taskBid);
        log.debug("返回内容为:" + JSON.toJSONString(taskBid));
        return result;
    }


}
