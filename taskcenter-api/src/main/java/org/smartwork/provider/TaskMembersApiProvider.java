package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TaskMembersController
 * @Description 任务参与人员查询
 * @Author xfx
 * @Date 2020/2/28 18:31
 * @Version 1.0
 */

@RestController
@RequestMapping("/taskmembers")
@Api(tags={"参与任务竞标人员查询"})
@Slf4j
public class TaskMembersApiProvider {

    @Autowired
    private IZGTaskBidService izgTaskBidService;

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ApiOperation("查询任务相关人员")
    public Result<List<SysUser>> list(@PathVariable long id){
        Result<List<SysUser>> result=new Result<List<SysUser>>();
      /*  List<IZGTaskBidService> izgTaskBidServiceList =izgTaskBidService.*/
        return result;
    }
}
