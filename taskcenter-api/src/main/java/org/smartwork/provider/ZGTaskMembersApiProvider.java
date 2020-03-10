package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.model.SysUser;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.comm.constant.DataColumnConstant;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.dal.entity.ZGTaskBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TaskMembersController
 * @Description 任务参与人员查询
 * @Author xfx
 * @Date 2020/2/28 18:31
 * @Version 1.0
 */

@RestController
@RequestMapping("/${smartwork.verision}/taskmembers")
@Api(tags={"参与任务竞标人员查询API"})
@Slf4j
public class ZGTaskMembersApiProvider {

    @Autowired
    private IZGTaskBidService izgTaskBidService;

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ApiOperation("查询任务相关人员")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code = 200, message = Result.COMM_ACTION_MSG)
    })

    public Result<List<ZGTaskBid>> list(@PathVariable long id){
        log.debug("参数为："+ JSON.toJSONString(id));
        QueryWrapper<ZGTaskBid> qw=new  QueryWrapper<ZGTaskBid>();
        Result<List<ZGTaskBid>> result=new Result<List<ZGTaskBid>>();
        qw.eq(DataColumnConstant.TASKID,id);
        /*根据任务id查询任务竞标记录*/
        List<ZGTaskBid> zgTaskBids=izgTaskBidService.list(qw);
        if(ConvertUtils.isEmpty(zgTaskBids)){
            result.setBizCode(TaskBizResultEnum.MEMBERS_NOT_EXIST.getBizCode());
            result.setMessage(TaskBizResultEnum.MEMBERS_NOT_EXIST.getBizMessage());
            return result;
        }
        result.setResult(zgTaskBids);
        return result;
    }
}
