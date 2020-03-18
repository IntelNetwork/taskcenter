package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.cache.UserCache;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.SysUserVo;
import org.smartwork.biz.service.*;
import org.smartwork.comm.constant.*;
import org.smartwork.comm.enums.*;
import org.smartwork.comm.model.ZGTaskPageDto;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.utils.UUIDGenerator;
import org.smartwork.comm.vo.ZGTaskCountVo;
import org.smartwork.comm.vo.ZGTaskVo;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.entity.ZGTaskBid;
import org.smartwork.dal.entity.ZGTaskOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.smartwork.comm.model.ZGTaskDto;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/${smartwork.verision}/task")
@Api(tags = {"API任务大厅,添加任务,任务列表等"})
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
     * @param taskDto 任务dto
     * @创建人 niehy(Frunk)
     * @创建时间 2020/2/29
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加任务")
    public Result<ZGTaskDto> addTask(@RequestBody @Validated(value = SaveValid.class) ZGTaskDto taskDto) {
        log.debug("传入参数为:" + JSON.toJSONString(taskDto));
        Result<ZGTaskDto> result = new Result<ZGTaskDto>();
        //传入实体类对象为空
        if (ConvertUtils.isEmpty(taskDto)) {
            result.setBizCode(TaskBizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        //任务金额小于0判断
        if (taskDto.getStartPrice().intValue() < 0 || taskDto.getEndPrice().intValue() <= 0) {
            result.setBizCode(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizCode());
            result.setMessage(TaskBizResultEnum.AMOUNT_LESS_ZERO.getBizMessage());
            return result;
        }
        //如果指定人不为空,说明此任务已指定服务方,不再走竞标流程,直接生成订单
        if (ConvertUtils.isNotEmpty(taskDto.getZgTaskBidDto())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstant.ORDER_PREFIX);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat(CommonConstant.YEAR_MONTH_FORMAT);
            taskDto.getZgTaskOrderDto().setSn(dateFormat.format(result.getTimestamp()) + dateFormat2.format(result.getTimestamp()));
            taskDto.getZgTaskOrderDto().setOrderStatus(TaskOrderStateEnum.UN_MANAGED.getCode());
            taskDto.getZgTaskOrderDto().setPayStatus(TaskPayStateEnum.UN_PAY.getCode());
            izgTaskOrderService.saveOrder(taskDto);
        }
        if (ConvertUtils.isNotEmpty(taskDto.getZgTaskBidDto())) {
            //如果指定了服务方
            //更改状态 任务:支付赏金,订单:未支付
            taskDto.setTaskState(TaskStateEnum.PAYMENT_GRATUITY.getCode());
            taskDto.getZgTaskOrderDto().setPayStatus(TaskPayStateEnum.UN_PAY.getCode());
        } else {
            //给定默认状态 待审核
            taskDto.setTaskState(TaskStateEnum.CHECK.getCode());
        }
        //加入需求方ID,用户名
        SysUserVo user = UserContext.getSysUser();
        taskDto.setMemberName(user.getUsername());
        taskDto.setMemberId(user.getId());
        //给定任务类型编码
        taskDto.setTypeCode(UUIDGenerator.generateString(8));
        //进入业务类继续操作
        taskService.addZGTask(taskDto);
        result.setResult(taskDto);
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
     * 方法概述:服务方提交验收任务
     * @param task 任务实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/submit-accept", method = RequestMethod.PUT)
    @ApiOperation("服务方提交验收任务")
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


    /***
     * 方法概述:需求方确认验收任务
     * @param task
     * @创建人 niehy(Frunk)
     * @创建时间 2020/3/16
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/confirm-accept", method = RequestMethod.PUT)
    @ApiOperation("需求方确认验收任务")
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
     * updateTask方法概述:任务编辑
     * @param task
     * @return org.smartwork.comm.model.ZGTaskDto
     * @创建人 Tom
     * @创建时间 2020/3/3 10:02
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/alter-task", method = RequestMethod.PUT)
    @ApiOperation("任务编辑")
    public Result<ZGTaskDto> updateTask(@RequestBody @Validated(value = UpdateValid.class) ZGTaskDto task) {
        log.debug("传入的参数为" + JSON.toJSONString(task));
        Result<ZGTaskDto> result = new Result<ZGTaskDto>();
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
        //如果任务处于待审核，审核未通过才能编辑
        if (task.getTaskState().equals(TaskStateEnum.CHECK_NULL.getCode()) |
                task.getTaskState().equals(TaskStateEnum.CHECK.getCode())) {
            //给定默认状态 待审核
            task.setTaskState(TaskStateEnum.CHECK.getCode());
            //给定任务类型编码
            task.setTypeCode(UUIDGenerator.generateString(8));
            taskService.updateTask(task);
            result.setResult(task);
        } else {
            result.setBizCode(TaskBizResultEnum.TASK_STATE_CHECK.getBizCode());
            result.setMessage(TaskBizResultEnum.TASK_STATE_CHECK.getBizMessage());
            return result;
        }
        return result;
    }

    /***
     * getByRelease方法概述:通过会员id查询已发布任务信息
     * @param
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/4 17:18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/get-release", method = RequestMethod.GET)
    @ApiOperation("通过会员id查询任务已发布信息")
    public Result<List<ZGTaskVo>> getByRelease() {
        Result<List<ZGTaskVo>> result = new Result<List<ZGTaskVo>>();
        //加入需求方(当前登录用户)ID
        SysUserVo user = UserContext.getSysUser();
        Long memberId=user.getId();
        //查询任务信息
        List<ZGTaskVo> zgTaskVos = taskService.getRelease(memberId);
        zgTaskVos.stream().forEach(zgTaskVo -> {
            int count = izgTaskOrderService.count(new QueryWrapper<ZGTaskOrder>().eq(DataColumnConstant.TASKID, zgTaskVo.getId()));
            if (count > 0) {
                ZGTaskOrder zgTaskOrder = izgTaskOrderService.selectOrder(zgTaskVo.getId(), memberId);
                zgTaskVo.setTaskMemberName(zgTaskOrder.getTaskMemberName());
            }
        });
        result.setResult(zgTaskVos);
        return result;
    }

    /***
     * getPass方法概述:通过会员id查询已完成任务信息
     * @param
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     * @创建人 Tom
     * @创建时间 2020/3/4 17:18
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/get-pass", method = RequestMethod.GET)
    @ApiOperation("通过会员id查询已完成任务信息")
    public Result<List<ZGTaskVo>> getPass() {
        Result<List<ZGTaskVo>> result = new Result<List<ZGTaskVo>>();
        //加入需求方(当前登录用户)ID
        SysUserVo user = UserContext.getSysUser();
        Long memberId=user.getId();

        //查询任务信息
        List<ZGTaskVo> zgTaskVos = taskService.getPass(memberId);
        zgTaskVos.stream().forEach(zgTaskVo -> {
            int count = izgTaskOrderService.count(new QueryWrapper<ZGTaskOrder>().eq(DataColumnConstant.TASKID, zgTaskVo.getId()));
            if (count > 0) {
                ZGTaskOrder zgTaskOrder = izgTaskOrderService.selectOrder(zgTaskVo.getId(), memberId);
                zgTaskVo.setTaskMemberName(zgTaskOrder.getTaskMemberName());
            }
        });
        result.setResult(zgTaskVos);
        return result;
    }

}
