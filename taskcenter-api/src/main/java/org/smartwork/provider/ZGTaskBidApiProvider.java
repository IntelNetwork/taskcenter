package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGBigAttachService;
import org.smartwork.biz.service.IZGTaskAttachService;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.DataColumnConstant;
import org.smartwork.comm.constant.TaskAttachColumnConstant;
import org.smartwork.comm.constant.TaskColumnConstant;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskHitstateEnum;
import org.smartwork.comm.enums.YesNoEnum;
import org.smartwork.comm.model.ZGBigAttachDto;
import org.smartwork.comm.model.ZGTaskBidDto;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.dal.entity.ZGBigAttach;
import org.smartwork.dal.entity.ZGTaskAttach;
import org.smartwork.dal.entity.ZGTaskBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    IZGTaskService taskService;

    @Autowired
    IZGBigAttachService bigAttachService;

    /***
     * 方法概述:查看竞标详情
     * @param id
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/bidding-detail", method = RequestMethod.PUT)
    @ApiOperation("查看竞标详情")

    public Result<ZGTaskBidDto> TaskBidDetail(@RequestParam Long id) {
        log.debug("传入参数为:" + id);
        Result<ZGTaskBidDto> result = new Result<ZGTaskBidDto>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(id)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        ZGTaskBidDto zgTaskBidDto = taskBidService.taskBidDetail(id);
        List<ZGBigAttachDto> zgBigAttachDtos = bigAttachService.listBidAttach(zgTaskBidDto.getId());
        zgTaskBidDto.setZgBigAttachDtos(zgBigAttachDtos);
        result.setResult(zgTaskBidDto);
        log.debug("返回内容为:" + JSON.toJSONString(zgTaskBidDto));
        return result;
    }


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

    public Result<ZGTaskBidDto> updateTaskBid(@RequestBody @Validated(value = UpdateValid.class) ZGTaskBidDto taskBidDto) {
        log.debug("传入参数为:" + JSON.toJSONString(taskBidDto));
        Result<ZGTaskBidDto> result = new Result<ZGTaskBidDto>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(taskBidDto)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        QueryWrapper<ZGTaskBid> query = new QueryWrapper<>();
        query.eq(DataColumnConstant.TASKID, taskBidDto.getTaskId());
        query.eq(DataColumnConstant.MEMBERID, taskBidDto.getMemberId());
        int count = taskBidService.count(query);
        if (count > 0) {
            result.setBizCode(TaskBizResultEnum.MEMBERS_SAME_EXIST.getBizCode());
            result.setMessage(TaskBizResultEnum.MEMBERS_SAME_EXIST.getBizMessage());
            return result;
        }
        //更改状态 审核中
        taskBidDto.setHitState(TaskHitstateEnum.CHECK.getCode());
        //进入业务类继续操作
        taskBidService.Bidding(taskBidDto);
        result.setResult(taskBidDto);
        log.debug("返回内容为:" + JSON.toJSONString(taskBidDto));
        return result;
    }

}
