package controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.enums.BizResultEnum;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.vo.Result;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * 方法概述:发布任务需求
     * @param task 任务实体类
     * @创建人 niehy(Frunk)
     * @创建时间 2020/2/29
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public Result<ZGTask> addTask(ZGTaskDto task){
        Result<ZGTask> result = new Result<ZGTask>();
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
        //给定默认状态
        task.setTaskState((long) 0);
        //进入业务类继续操作
        taskService.addZGTask(task);
        return result;
    }
    
    
    


}
