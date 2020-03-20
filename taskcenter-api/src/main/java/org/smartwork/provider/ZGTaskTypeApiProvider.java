package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.smartwork.biz.service.IZGTaskTypeService;
import org.smartwork.dal.entity.ZGTaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.forbes.comm.vo.Result;
import java.util.List;

/**
 * @ClassName TaskTypeController
 * @Description 任务类型
 * @Author xfx
 * @Date 2020/2/28 17:49
 * @Version 1.0
 */
@RestController
@RequestMapping("/${smartwork.verision}/task-type")
@Api(tags={"API任务类型"})
@Slf4j
public class ZGTaskTypeApiProvider {

    @Autowired
    private IZGTaskTypeService taskTypeService;

    /**
     * @Author xfx
     *  @Date 18:05 2020/2/28
     * @Param []
     * @return org.forbes.comm.vo.Result<java.util.List<org.smartwork.dal.entity.ZGTaskType>>
     **/
    @RequestMapping(value = "/aggregate", method = RequestMethod.GET)
    @ApiOperation("查询任务类型")
    public Result<List<ZGTaskType>> list(){
        Result<List<ZGTaskType>> result=new Result<List<ZGTaskType>>();
        List<ZGTaskType> zgTaskTypeList=taskTypeService.list();
        result.setResult(zgTaskTypeList);
        return result;
    }
}
