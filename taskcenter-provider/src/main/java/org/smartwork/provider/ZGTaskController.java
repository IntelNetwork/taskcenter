package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskStateEnum;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = {"任务管理"})
@RestController
@RequestMapping("/zgtask")
@Slf4j
public class ZGTaskController extends BaseProvider<IZGTaskService, ZGTask> {

    @Autowired
    IZGTaskService taskService;

    /***
     * 方法概述:后台任务审核
     * @param task
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/audit", method = RequestMethod.PUT)
    @ApiOperation("后台任务审核")
    public Result<ZGTask> auditTask(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //只有待审核状态的任务才可以被审核
        if (task.getTaskState().equalsIgnoreCase(TaskStateEnum.CHECK.getCode())) {
            //更改状态 竞标中
            task.setTaskState(TaskStateEnum.RELEASE.getCode());
            taskService.updateById(task);
            result.setResult(task);
        }
        log.debug("返回内容为:" + JSON.toJSONString(task));
        return result;
    }
}