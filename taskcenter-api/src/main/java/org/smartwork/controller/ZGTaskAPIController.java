package org.smartwork.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.BasePageDto;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.TaskColumnConstant;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.BizResultEnum;
import org.smartwork.comm.enums.TaskStateEnum;
import org.smartwork.comm.model.ZGTaskPageDto;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.vo.Result;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.smartwork.comm.model.ZGTaskDto;

import java.util.List;
import java.util.Map;

/***
 * 类概述:任务API控制层
 * @创建人 niehy(Frunk)
 * @创建时间 2020/2/29
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/api-task")
@Api(tags = {"任务API控制层"})
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加任务")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_ADD_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_ADD)
    })
    public Result<ZGTaskDto> addTask(ZGTaskDto task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTaskDto> result = new Result<ZGTaskDto>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
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
            //薪资为空d
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
    @RequestMapping(value = "/release", method = RequestMethod.PUT)
    @ApiOperation("发布任务")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_RELEASE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_RELEASE)
    })
    public Result<ZGTask> updateTask(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //更改状态 已发布
        task.setTaskState(TaskStateEnum.RELEASE.getCode());
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
    @RequestMapping(value = "/lower", method = RequestMethod.PUT)
    @ApiOperation("下架任务")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_RELEASE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_RELEASE)
    })
    public Result<ZGTask> lowerTask(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //更改状态 已下架
        task.setTaskState(TaskStateEnum.LOWER_SHELF.getCode());
        //进入业务类继续操作
        taskService.updateById(task);
        result.setResult(task);
        log.debug("返回内容为:" + JSON.toJSONString(task));
        return result;
    }


    /***
     * 方法概述:获取任务状态枚举值
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/3
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ApiOperation("获取任务状态枚举值")
    public Result<List<Map<String, String>>> receNewsStatus() {
        Result<List<Map<String, String>>> result = new Result<List<Map<String, String>>>();
        result.setResult(TaskStateEnum.receTaskStateEnum());
        return result;
    }



    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("删除任务")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "任务ID", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_DELETE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_DELETE)
    })
    public Result<ZGTask> deleteNews(@RequestParam(value = "id", required = true) String id) {
        log.debug("传入参数为:" + JSON.toJSONString(id));
        Result<ZGTask> result = new Result<ZGTask>();
        ZGTask task = taskService.getById(id);
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        taskService.removeTask(id);
        log.debug("返回值为:" + JSON.toJSONString(result.getResult()));
        return result;
    }



    /***
     * deleteBatch方法概述:批量删除任务
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/17
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/delete-batch", method = RequestMethod.DELETE)
    @ApiOperation("批量删除任务")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_DELETE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_DELETE)
    })
    public Result<ZGTask> deleteBatch(@RequestParam("ids") String ids) {
        log.debug("传入参数为:" + JSON.toJSONString(ids));
        Result<ZGTask> result = new Result<ZGTask>();
        taskService.removeTasks(ids);
        log.debug("返回值为:" + JSON.toJSONString(result.getResult()));
        return result;
    }


    /***
     * pageTasks方法概述:任务分页查询
     * @param basePageDto, zgTaskPageDto
     * @return org.forbes.comm.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<org.smartwork.dal.entity.ZGTask>>
     * @创建人 Tom
     * @创建时间 2020/3/2 13:41
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation("任务分页查询")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_SELECT_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_SELECT)
    })
    public org.forbes.comm.vo.Result<IPage<ZGTask>> selectUserList(BasePageDto basePageDto, ZGTaskPageDto zgTaskPageDto){
        log.debug("传入的参数为"+JSON.toJSONString(basePageDto));
        org.forbes.comm.vo.Result<IPage<ZGTask>> result = new org.forbes.comm.vo.Result<>();
        QueryWrapper<ZGTask> qw = new QueryWrapper<ZGTask>();
        if(org.forbes.comm.utils.ConvertUtils.isNotEmpty(zgTaskPageDto)){
            if(org.forbes.comm.utils.ConvertUtils.isNotEmpty(zgTaskPageDto.getTTypeName())){
                qw.eq(TaskColumnConstant.TTYPENAME,zgTaskPageDto.getTTypeName());
            }
            if(org.forbes.comm.utils.ConvertUtils.isNotEmpty(zgTaskPageDto.getIndustry())){
                qw.eq(TaskColumnConstant.INDUSTRY,zgTaskPageDto.getIndustry());
            }
        }
        IPage<ZGTask> page = new Page<ZGTask>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ZGTask> s = taskService.page(page,qw);
        result.setResult(s);
        log.info("返回值为:"+JSON.toJSONString(result.getResult()));
        return result;
    }

    /***
     * selectAllTask方法概述:获取最新成交动态
     * @param
     * @return org.forbes.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGTask>>
     * @创建人 Tom
     * @创建时间 2020/3/2 18:20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @ApiOperation("获取最新成交动态")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_SELECT_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_SELECT)
    })
    public org.forbes.comm.vo.Result<List<ZGTask>> selectAllTask(){
        org.forbes.comm.vo.Result<List<ZGTask>> result=new org.forbes.comm.vo.Result<>();
        List<ZGTask> tasks = taskService.selectAllTask();
        result.setResult(tasks);
        return result;
    }

    /***
     * AllCount方法概述:任务总数查询
     * @param
     * @return org.forbes.comm.vo.Result<java.lang.Integer>
     * @创建人 Tom
     * @创建时间 2020/3/3 9:21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/all-count", method = RequestMethod.GET)
    @ApiOperation("任务总数查询")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_SELECT_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_SELECT)
    })
    public org.forbes.comm.vo.Result<Integer> AllCount(){
        org.forbes.comm.vo.Result<Integer> result=new org.forbes.comm.vo.Result<>();
        Integer tasks=taskService.count();
        result.setResult(tasks);
        return result;
    }

    /***
     * updateTask方法概述:任务编辑
     * @param zgTaskDto
     * @return org.forbes.comm.vo.Result<org.smartwork.comm.model.ZGTaskDto>
     * @创建人 Tom
     * @创建时间 2020/3/3 10:02
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ApiOperation("任务编辑")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.TASK_UPDATE_ERROR),
            @ApiResponse(code = 200, message = Result.TASK_UPDATE)
    })
    public org.forbes.comm.vo.Result<ZGTaskDto> updateTask(@RequestBody @Validated(value= org.forbes.comm.constant.UpdateValid.class) ZGTaskDto zgTaskDto){
        log.debug("传入的参数为"+JSON.toJSONString(zgTaskDto));
        org.forbes.comm.vo.Result<ZGTaskDto> result=new org.forbes.comm.vo.Result<ZGTaskDto>();
        try {
            ZGTask oldZgTask = taskService.getById(zgTaskDto.getId());
            if(org.forbes.comm.utils.ConvertUtils.isEmpty(oldZgTask)){
                result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
                result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
                return result;
            }
            String code = zgTaskDto.getTTypeCode();
            //判断当前任务类型编码与输入的是否一致
            if (!code.equalsIgnoreCase(oldZgTask.getTTypeCode())) {
                //查询是否和其他任务类型编码一致
                int existsCount = taskService.count(new QueryWrapper<ZGTask>().eq(TaskColumnConstant.TTYPECODE, code));
                //存在此记录
                if (existsCount > 0) {
                    result.setBizCode(BizResultEnum.TASK_CODE_EXISTS.getBizCode());
                    result.setMessage(String.format(BizResultEnum.TASK_CODE_EXISTS.getBizFormateMessage(), code));
                    return result;
                }
            }
            taskService.updateTask(zgTaskDto);
            result.setResult(zgTaskDto);
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }
}
