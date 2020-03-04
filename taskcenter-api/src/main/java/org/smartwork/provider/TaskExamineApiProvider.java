package org.smartwork.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.comm.constant.DataColumnConstant;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.BizResultEnum;
import org.smartwork.comm.enums.TaskHitstateEnum;
import org.smartwork.comm.utils.ConvertUtils;
import org.smartwork.comm.vo.Result;
import org.smartwork.dal.entity.ZGTaskBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO
 * @author: xfx
 * @date: Created in 2020/3/3 15:00
 * @version: 1.0
 * @modified By:
 */
@RestController
@RequestMapping("/examine")
@Api(tags={"选标"})
@Slf4j
public class TaskExamineApiProvider {


    @Autowired
    private IZGTaskBidService izgTaskBidService;


    /**
     * @description
     * @author xfx
     * @date 2020/3/4 9:48
     * @parameter [zgTaskBid]
     * @return org.smartwork.comm.vo.Result<org.smartwork.dal.entity.ZGTaskBid>
     */
    @RequestMapping(value = "/check",method = RequestMethod.PUT)
    @ApiOperation("选标")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = Result.EXAMINE_ERROR),
            @ApiResponse(code = 200, message = Result.EXAMINE)
    })
    public Result<ZGTaskBid> check(@RequestBody @Validated(value = UpdateValid.class) ZGTaskBid zgTaskBid){
        log.debug(JSON.toJSONString("传入参数为："+zgTaskBid));
        Result<ZGTaskBid> result=new Result<ZGTaskBid>();
        if(ConvertUtils.isEmpty(zgTaskBid)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        ZGTaskBid zgTaskBid_temp=izgTaskBidService.getById(zgTaskBid.getId());
        QueryWrapper<ZGTaskBid> qw=new QueryWrapper<ZGTaskBid>();
        qw.eq(DataColumnConstant.TASKID,zgTaskBid.getTaskId());
        qw.eq(DataColumnConstant.HITSTATE,TaskHitstateEnum.HITSTATE.getCode());
        Integer count=izgTaskBidService.count(qw);
        if(ConvertUtils.isEmpty(zgTaskBid_temp)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        if(count!=1){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        izgTaskBidService.updateById(zgTaskBid);
        result.setResult(zgTaskBid);
        return result;
    }
}
