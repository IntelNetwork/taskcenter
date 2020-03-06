package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.biz.service.IZGTaskService;
import org.smartwork.comm.constant.DataColumnConstant;
import org.smartwork.comm.enums.TaskBizResultEnum;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.vo.SysUser;
import org.smartwork.comm.vo.ZGTaskVo;
import org.smartwork.dal.entity.ZGTask;
import org.smartwork.dal.entity.ZGTaskBid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName TaskDetailController
 * @Description 任务详情
 * @Author xfx
 * @Date 2020/2/28 18:16
 * @Version 1.0
 */

@RestController
@RequestMapping("/${smartwork.verision}/taskdetail")
@Api(tags={"任务详情API"})
@Slf4j
public class ZGTaskDetailApiProvider {

    @Autowired
    private IZGTaskService izgTaskService;

    @Autowired
    private IZGTaskBidService izgTaskBidService;


    /**
     * @Author xfx
     * @Date 18:45 2020/2/28
     * @Param [id] 任务id
     * @return org.forbes.comm.vo.Result<org.smartwork.dal.entity.ZGTask>
     **/
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ApiOperation("查询任务详情")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code = 200, message = Result.COMM_ACTION_MSG)
    })
    public Result<ZGTaskVo> detail(@PathVariable long id,@RequestBody SysUser sysUser){
        log.debug("传入的参数为"+ JSON.toJSONString(id)+"SysUser:"+sysUser);
        Result<ZGTaskVo> result=new Result<ZGTaskVo>();
        if(ConvertUtils.isEmpty(sysUser)||ConvertUtils.isEmpty(id)){
            result.setBizCode(TaskBizResultEnum.EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        ZGTaskVo zgTaskVo=new ZGTaskVo();
        ZGTask zgTask=izgTaskService.getById(id);
        BeanUtils.copyProperties(zgTask, zgTaskVo);
        QueryWrapper<ZGTaskBid> qw=new QueryWrapper<ZGTaskBid>();
        qw.eq(DataColumnConstant.TASKID,id);//任务id
        //获取当前用户id
        qw.eq(DataColumnConstant.MEMBERID,sysUser.getId());
        /*根据用户id，任务id查询该用户竞标状态*/
        ZGTaskBid zgTaskBid=izgTaskBidService.getOne(qw);
        if(ConvertUtils.isNotEmpty(zgTaskVo)&&ConvertUtils.isNotEmpty(zgTaskBid)){
            zgTaskVo.setHitState(zgTaskBid.getHitState());
            result.setResult(zgTaskVo);
        }else {
            result.setBizCode(TaskBizResultEnum.EMPTY.getBizCode());
            result.setMessage(TaskBizResultEnum.EMPTY.getBizMessage());
            return result;
        }
        return  result;
    }
}
