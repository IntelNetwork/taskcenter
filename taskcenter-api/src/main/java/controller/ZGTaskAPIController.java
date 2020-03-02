package controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.BizResultEnum;
import org.smartwork.comm.enums.TaskStateEnum;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.vo.Result;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.smartwork.comm.model.ZGTaskDto;

/***
 * 类概述:任务API控制层
 * @创建人 niehy(Frunk)
 * @创建时间 2020/2/29
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/api-task")
@Api(tags={"任务API控制层"})
@Slf4j
public class ZGTaskAPIController {

    
    @Autowired
    IZGTaskService taskService;


    /***
     * 方法概述:添加任务
     * @param task 任务实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/2/29
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation("添加任务")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_ADD_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_ADD)
    })
    public Result<ZGTaskDto> addTask(ZGTaskDto task){
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTaskDto> result = new Result<ZGTaskDto>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        if (ConvertUtils.isEmpty(task.getName())) {
            //任务名称为空
            result.setBizCode(BizResultEnum.EMPTY.getBizCode());
            result.setMessage(BizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        if (ConvertUtils.isEmpty(task.getTTypeName())) {
            //任务类型为空
            result.setBizCode(BizResultEnum.EMPTY.getBizCode());
            result.setMessage(BizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        if (ConvertUtils.isEmpty(task.getTPeriod())) {
            //任务期限为空
            result.setBizCode(BizResultEnum.EMPTY.getBizCode());
            result.setMessage(BizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        if (ConvertUtils.isEmpty(task.getTStartPrice()) || ConvertUtils.isEmpty(task.getTEndPrice())) {
            //薪资为空
            result.setBizCode(BizResultEnum.EMPTY.getBizCode());
            result.setMessage(BizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        if (ConvertUtils.isEmpty(task.getTDes())) {
            //任务描述为空
            result.setBizCode(BizResultEnum.EMPTY.getBizCode());
            result.setMessage(BizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        //给定默认状态 未发布
        task.setTaskState(TaskStateEnum.UNPUBLISHED.getCode());
        //进入业务类继续操作
        taskService.addZGTask(task);
        result.setResult(task);
        return result;
    }


    /***
     * 方法概述:发布任务
     * @param task 任务实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/release",method = RequestMethod.PUT)
    @ApiOperation("发布任务")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_RELEASE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_RELEASE)
    })
    public Result<ZGTask> updateTask(@RequestBody @Validated(value = UpdateValid.class)ZGTask task){
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //更改状态 下架
        task.setTaskState((long) 1);
        //进入业务类继续操作
        taskService.updateById(task);
        result.setResult(task);
        log.debug("返回内容为:" + JSON.toJSONString(task));
        return result;
    }



    /***
     * 方法概述:下架任务
     * @param task 任务实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/lower",method = RequestMethod.PUT)
    @ApiOperation("下架任务")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_RELEASE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_RELEASE)
    })
    public Result<ZGTask> lowerTask(@RequestBody @Validated(value = UpdateValid.class)ZGTask task){
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //更改状态 已发布
        task.setTaskState((long) 1);
        //进入业务类继续操作
        taskService.updateById(task);
        result.setResult(task);
        log.debug("返回内容为:" + JSON.toJSONString(task));
        return result;
    }


}
