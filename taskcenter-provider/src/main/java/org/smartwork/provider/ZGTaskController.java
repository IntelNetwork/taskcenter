package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.CommonConstant;
import org.smartwork.comm.constant.TaskBidCommonConstant;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskHitstateEnum;
import org.smartwork.comm.enums.TaskStateEnum;
import org.smartwork.comm.model.AuditTaskDto;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.entity.ZGTaskBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Api(tags = {"任务管理"})
@RestController
@RequestMapping("/zgtask")
@Slf4j
public class ZGTaskController extends BaseProvider<IZGTaskService, ZGTask> {

    @Autowired
    IZGTaskService taskService;

    @Autowired
    IZGTaskBidService taskBidService;

    /***
     * 方法概述:后台任务审核
     * @param auditTaskDto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/audit", method = RequestMethod.PUT)
    @ApiOperation("后台任务审核")
    public Result<ZGTask> auditTask(@RequestBody AuditTaskDto auditTaskDto) {
        Result<ZGTask> result = new Result<>();
        ZGTask task = taskService.getById(auditTaskDto.getId());
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //如果传入为审核不通过,则直接改为不通过
        if (auditTaskDto.getTaskState().equalsIgnoreCase(TaskStateEnum.CHECK_NULL.getCode())) {
            task.setTaskState(TaskStateEnum.CHECK_NULL.getCode());
        } else {
            ZGTaskBid taskBid = taskBidService.getOne(new QueryWrapper<ZGTaskBid>().eq(TaskBidCommonConstant.TASK_ID, auditTaskDto.getId()));
            if (ConvertUtils.isNotEmpty(taskBid)) {
                //指定人不为空说明指定了服务方并且中标状态为已中标,更改状态为支付赏金
                if (ConvertUtils.isNotEmpty(taskBid.getMemberName()) &&
                        taskBid.getHitState().equalsIgnoreCase(TaskHitstateEnum.HITSTATE.getCode())) {
                    task.setTaskState(TaskStateEnum.PAYMENT_GRATUITY.getCode());
                }
            } else {
                //指定人为空的时候说明需要进入选标中
                if (task.getTaskState().equalsIgnoreCase(TaskStateEnum.CHECK.getCode())) {
                    task.setTaskState(TaskStateEnum.RELEASE.getCode());
                } else {
                    result.setBizCode(TaskBizResultEnum.TASK_NOT_CHECK.getBizCode());
                    result.setMessage(TaskBizResultEnum.TASK_NOT_CHECK.getBizMessage());
                    return result;
                }
            }

        }
        //加入发布时间
        task.setReleaseTime(new Date());
        taskService.updateById(task);
        result.setResult(task);
        return result;
    }
}