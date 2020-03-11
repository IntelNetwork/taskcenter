package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.*;
import org.smartwork.comm.constant.*;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.enums.TaskOrderStateEnum;
import org.smartwork.comm.enums.TaskPayStateEnum;
import org.smartwork.comm.enums.TaskStateEnum;
import org.smartwork.comm.model.ZGTaskPageDto;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.utils.UUIDGenerator;
import org.smartwork.comm.vo.ZGTaskCountVo;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.entity.ZGTaskBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.smartwork.comm.model.ZGTaskDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/***
 * 类概述:任务API控制层
 * @创建人 niehy(Frunk)
 * @创建时间 2020/2/29
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@RestController
@RequestMapping("/${smartwork.verision}/task")
@Api(tags = {"任务大厅,添加任务,任务列表等"})
@Slf4j
public class ZGTaskAPIProvider {


    @Autowired
    IZGTaskService taskService;
    @Autowired
    IZGTaskAttachService zgTaskAttachService;
    @Autowired
    IZGTaskRelTagService zgTaskRelTagService;
    @Autowired
    IZGTaskBidService zgTaskBidService;
    @Autowired
    IZGTaskOrderService izgTaskOrderService;

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
    public Result<ZGTaskDto> addTask(@RequestBody @Validated(value = SaveValid.class) ZGTaskDto task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTaskDto> result = new Result<ZGTaskDto>();
        //如果指定人不为空,说明此任务已指定服务方,不再走竞标流程,直接生成订单
        if (ConvertUtils.isNotEmpty(task.getZgTaskBidDto())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.ORDER_PREFIX);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat(CommonConstant.YEAR_MONTH_FORMAT);

            task.getZgTaskOrderDto().setSn(dateFormat.format(result.getTimestamp()) + dateFormat2.format(result.getTimestamp()));
            task.getZgTaskOrderDto().setOrderStatus(TaskOrderStateEnum.UN_MANAGED.getCode());
            task.getZgTaskOrderDto().setPayStatus(TaskPayStateEnum.UN_PAY.getCode());
            izgTaskOrderService.saveOrder(task.getZgTaskOrderDto());
            return result;
        }
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //任务金额小于0判断
        if (task.getStartPrice().intValue() < 0 || task.getEndPrice().intValue() < 0) {
            result.setBizCode(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizCode());
            result.setMessage(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizMessage());
            return result;
        }
        //给定默认状态 待审核
        task.setTaskState(TaskStateEnum.CHECK.getCode());
        //给定任务类型编码
        task.setTypeCode(UUIDGenerator.generateString(8));
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
    public Result<ZGTask> updateTask(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //任务已经发布过,请勿重复发布
        if (task.getTaskState().equalsIgnoreCase(TaskStateEnum.RELEASE.getCode())) {
            result.setBizCode(TaskBizResultEnum.TASK_RELEASE.getBizCode());
            result.setMessage(TaskBizResultEnum.TASK_RELEASE.getBizMessage());
            return result;
        }
        //任务只有在未发布和已下架时可重新发布任务
        if (task.getTaskState().equalsIgnoreCase(TaskStateEnum.UNPUBLISHED.getCode()) && task.getTaskState().equalsIgnoreCase(TaskStateEnum.LOWER_SHELF.getCode())) {
            //更改状态 待审核
            task.setTaskState(TaskStateEnum.CHECK.getCode());
            //添加发布时间
            task.setReleaseTime(new Date());
            taskService.updateById(task);
        }
        result.setResult(task);
        log.debug("返回内容为:" + JSON.toJSONString(task));
        return result;
    }



    /***
     * 方法概述:支付之后托管赏金
     * @param task 任务实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/trust-reward", method = RequestMethod.PUT)
    @ApiOperation("支付之后托管赏金")
    public Result<ZGTask> trustReward(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {

        Result<ZGTask> result = taskService.trustReward(task);

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
    public Result<ZGTask> lowerTask(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //只有在无人竞标的情况下才可以下架任务
        int count = zgTaskBidService.count(new QueryWrapper<ZGTaskBid>().eq(DataColumnConstant.TASKID, task.getId()));
        if (count > 0) {
            result.setBizCode(TaskBizResultEnum.BIDDING_NUMBER_NOT_.getBizCode());
            result.setMessage(TaskBizResultEnum.BIDDING_NUMBER_NOT_.getBizMessage());
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
     * 方法概述:开始任务
     * @param task 任务实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/start", method = RequestMethod.PUT)
    @ApiOperation("开始任务")
    public Result<ZGTask> StartTask(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //只有托管赏金之后才可以开始工作
        if (task.getTaskState().equalsIgnoreCase(TaskStateEnum.TRUST_REWARD.getCode())) {
            //更改状态 开始工作
            task.setTaskState(TaskStateEnum.START_UP.getCode());
            taskService.updateById(task);
            result.setResult(task);
        }
        log.debug("返回内容为:" + JSON.toJSONString(task));
        return result;
    }


    /***
     * 方法概述:提交验收任务
     * @param task 任务实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/submit-accept", method = RequestMethod.PUT)
    @ApiOperation("提交验收任务")
    public Result<ZGTask> submitTask(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //只有任务开始后才可以提交验收,其他情况不能
        if (task.getTaskState().equalsIgnoreCase(TaskStateEnum.START_UP.getCode())) {
            //更改状态 提交验收
            task.setTaskState(TaskStateEnum.SUBMIT_ACCEPTANCE.getCode());
            taskService.updateById(task);
            result.setResult(task);
        }
        log.debug("返回内容为:" + JSON.toJSONString(task));
        return result;
    }


    /***
     * 方法概述:确认验收任务
     * @param task
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/confirm-accept", method = RequestMethod.PUT)
    @ApiOperation("确认验收任务")
    public Result<ZGTask> confirmTask(@RequestBody @Validated(value = UpdateValid.class) ZGTask task) {
        log.debug("传入参数为:" + JSON.toJSONString(task));
        Result<ZGTask> result = new Result<ZGTask>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //只有提交验收才可以确认验收
        if (task.getTaskState().equalsIgnoreCase(TaskStateEnum.SUBMIT_ACCEPTANCE.getCode())) {
            //更改状态 确认验收
            task.setTaskState(TaskStateEnum.CONFIRMATION_ACCEPTANCE.getCode());
            task.setEndTime(new Date());
            taskService.updateById(task);
            result.setResult(task);
        }


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
    public Result<ZGTask> deleteNews(@RequestParam(value = "id", required = true) String id) {
        log.debug("传入参数为:" + JSON.toJSONString(id));
        Result<ZGTask> result = new Result<ZGTask>();
        ZGTask task = taskService.getById(id);
        if (ConvertUtils.isEmpty(task)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
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
     * @return com.baomidou.mybatisplus.core.metadata.IPage<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/2 13:41
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("任务分页查询")
    public Result<IPage<ZGTaskCountVo>> selectUserList(BasePageDto basePageDto, ZGTaskPageDto zgTaskPageDto) {
        log.debug("传入的参数为" + JSON.toJSONString(basePageDto));
        Result<IPage<ZGTaskCountVo>> result = new Result<IPage<ZGTaskCountVo>>();
        IPage<ZGTaskCountVo> page = new Page<ZGTaskCountVo>(basePageDto.getPageNo(), basePageDto.getPageSize());
        IPage<ZGTaskCountVo> pageUsers = taskService.pageTasks(page, zgTaskPageDto);
        pageUsers.getRecords().stream().forEach(task -> {
            int count = zgTaskBidService.count(new QueryWrapper<ZGTaskBid>().eq(TaskColumnConstant.TASKID, task.getId()));
            task.setCount(count);
        });
        result.setResult(pageUsers);
        return result;
    }

    /***
     * selectAllTask方法概述:获取最新成交动态
     * @param
     * @return org.smartwork.dal.entity.ZGTask
     * @创建人 Tom
     * @创建时间 2020/3/2 18:20
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @ApiOperation("获取最新成交动态")
    public Result<List<ZGTask>> selectAllTask() {
        Result<List<ZGTask>> result = new Result<>();
        List<ZGTask> tasks = taskService.selectAllTask();
        result.setResult(tasks);
        return result;
    }

    /***
     * AllCount方法概述:任务总数查询
     * @param
     * @return java.lang.Integer
     * @创建人 Tom
     * @创建时间 2020/3/3 9:21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/all-count", method = RequestMethod.GET)
    @ApiOperation("任务总数查询")
    public Result<Integer> AllCount() {
        Result<Integer> result = new Result<>();
        Integer tasks = taskService.count();
        result.setResult(tasks);
        return result;
    }


    /***
     * updateTask方法概述:任务编辑
     * @param task
     * @return org.smartwork.comm.model.ZGTaskDto
     * @创建人 Tom
     * @创建时间 2020/3/3 10:02
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("任务编辑")
    public Result<ZGTaskDto> updateTask(@RequestBody @Validated(value = UpdateValid.class) ZGTaskDto task) {
        log.debug("传入的参数为" + JSON.toJSONString(task));
        Result<ZGTaskDto> result = new Result<ZGTaskDto>();
        try {
            String taskState = task.getTaskState();
            //判断任务是否待审核
            if (taskState.equals("1")) {
                result.setBizCode(TaskBizResultEnum.TASK_STATE_CHECK.getBizCode());
                result.setMessage(String.format(TaskBizResultEnum.TASK_STATE_CHECK.getBizFormateMessage(), taskState));
                return result;
            }
            ZGTask oldZgTask = taskService.getById(task.getId());
            if (ConvertUtils.isEmpty(oldZgTask)) {
                result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
                result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
                return result;
            }
            String code = task.getTypeCode();
            //判断当前任务类型编码与输入的是否一致
            if (!code.equalsIgnoreCase(oldZgTask.getTypeCode())) {
                //查询是否和其他任务类型编码一致
                int existsCount = taskService.count(new QueryWrapper<ZGTask>().eq(TaskColumnConstant.TTYPECODE, code));
                //存在此记录
                if (existsCount > 0) {
                    result.setBizCode(TaskBizResultEnum.TASK_CODE_EXISTS.getBizCode());
                    result.setMessage(String.format(TaskBizResultEnum.TASK_CODE_EXISTS.getBizFormateMessage(), code));
                    return result;
                }
            }
            taskService.updateTask(task);
            result.setResult(task);
        } catch (ForbesException e) {
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }

    /***
     * getByMemberId方法概述:通过会员id查询任务信息
     * @param memberId
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/4 17:18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/emand-list", method = RequestMethod.GET)
    @ApiOperation("通过会员id查询任务信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "memberId", value = "会员id")
    )
    public Result<List<ZGTask>> getByMemberId(@RequestParam(value = "memberId", required = true) Long memberId) {
        Result<List<ZGTask>> result = new Result<List<ZGTask>>();
        //查询任务信息
        List<ZGTask> zgTask = taskService.list(new QueryWrapper<ZGTask>().eq(TaskColumnConstant.MEMBERID, memberId));
        result.setResult(zgTask);
        return result;
    }

}
