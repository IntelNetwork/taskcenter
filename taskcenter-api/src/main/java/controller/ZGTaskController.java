package controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.TaskColumnConstant;
import org.smartwork.comm.enums.BizResultEnum;
import org.smartwork.comm.model.ZGTaskDto;
import org.smartwork.comm.model.ZGTaskPageDto;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lzw
 * @date 2020/3/2 12:38
 */
@RestController
@RequestMapping("/api-task")
@Api(tags={"任务管理"})
@Slf4j
public class ZGTaskController {

    @Autowired
    IZGTaskService izgTaskService;

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
    public Result<IPage<ZGTask>> selectUserList(BasePageDto basePageDto, ZGTaskPageDto zgTaskPageDto){
        log.debug("传入的参数为"+JSON.toJSONString(basePageDto));
        Result<IPage<ZGTask>> result = new Result<>();
        QueryWrapper<ZGTask> qw = new QueryWrapper<ZGTask>();
        if(ConvertUtils.isNotEmpty(zgTaskPageDto)){
            if(ConvertUtils.isNotEmpty(zgTaskPageDto.getTTypeName())){
                qw.eq(TaskColumnConstant.TTYPENAME,zgTaskPageDto.getTTypeName());
            }
            if(ConvertUtils.isNotEmpty(zgTaskPageDto.getIndustry())){
                qw.eq(TaskColumnConstant.INDUSTRY,zgTaskPageDto.getIndustry());
            }
        }
        IPage<ZGTask> page = new Page<ZGTask>(basePageDto.getPageNo(),basePageDto.getPageSize());
        IPage<ZGTask> s = izgTaskService.page(page,qw);
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
    public Result<List<ZGTask>> selectAllTask(){
        Result<List<ZGTask>> result=new Result<>();
        List<ZGTask> tasks = izgTaskService.selectAllTask();
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
    public Result<Integer> AllCount(){
        Result<Integer> result=new Result<>();
        Integer tasks=izgTaskService.count();
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
    public Result<ZGTaskDto> updateTask(@RequestBody @Validated(value=UpdateValid.class) ZGTaskDto zgTaskDto){
        log.debug("传入的参数为"+JSON.toJSONString(zgTaskDto));
        Result<ZGTaskDto> result=new Result<ZGTaskDto>();
        try {
            ZGTask oldZgTask = izgTaskService.getById(zgTaskDto.getId());
            if(ConvertUtils.isEmpty(oldZgTask)){
                result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
                result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
                return result;
            }
            String code = zgTaskDto.getTTypeCode();
            //判断当前任务类型编码与输入的是否一致
            if (!code.equalsIgnoreCase(oldZgTask.getTTypeCode())) {
                //查询是否和其他任务类型编码一致
                int existsCount = izgTaskService.count(new QueryWrapper<ZGTask>().eq(TaskColumnConstant.TTYPECODE, code));
                //存在此记录
                if (existsCount > 0) {
                    result.setBizCode(BizResultEnum.TASK_CODE_EXISTS.getBizCode());
                    result.setMessage(String.format(BizResultEnum.TASK_CODE_EXISTS.getBizFormateMessage(), code));
                    return result;
                }
            }
            izgTaskService.updateTask(zgTaskDto);
            result.setResult(zgTaskDto);
        }catch(ForbesException e){
            result.setBizCode(e.getErrorCode());
            result.setMessage(e.getErrorMsg());
        }
        return result;
    }

}
