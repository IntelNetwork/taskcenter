package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.dal.entity.ZGTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TaskDetailController
 * @Description 任务详情
 * @Author xfx
 * @Date 2020/2/28 18:16
 * @Version 1.0
 */

@RestController
@RequestMapping("/taskdetail")
@Api(tags={"任务详情"})
@Slf4j
public class TaskDetailController {

    @Autowired
    private IZGTaskService izgTaskService;
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ApiOperation("查询任务详情")
    /**
     * @Author xfx
     *  @Date 18:45 2020/2/28
     * @Param [id] 任务id
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     **/
    public Result<ZGTask> detail(@PathVariable long id){
        Result<ZGTask> result=new Result<ZGTask>();
        ZGTask zgTask=izgTaskService.getById(id);
        result.setResult(zgTask);
        return  result;
    }
}
