package controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.smartwork.biz.service.IZGTaskBidService;
import org.smartwork.comm.constant.UpdateValid;
import org.smartwork.comm.enums.BizResultEnum;
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
@Api(tags={"竞标审核"})
@Slf4j
public class TaskExamineController {


    @Autowired
    private IZGTaskBidService izgTaskBidService;

    @RequestMapping(value = "/check",method = RequestMethod.PUT)
    @ApiOperation("竞标审核")
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
        if(ConvertUtils.isEmpty(zgTaskBid_temp)){
            result.setBizCode(org.forbes.comm.enums.BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(org.forbes.comm.enums.BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        izgTaskBidService.updateById(zgTaskBid);
        result.setResult(zgTaskBid);
        return result;
    }
}
