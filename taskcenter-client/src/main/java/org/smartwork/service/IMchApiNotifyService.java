package org.smartwork.service;

import org.forbes.comm.vo.Result;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@ConditionalOnProperty(name = "spring.application.paycenter")
@FeignClient(name = "${spring.application.paycenter}")
public interface IMchApiNotifyService {


    /***
     * 回写成功状态
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/notify-success",method = RequestMethod.PUT)
    Result<Object>  notifySuccess(@RequestParam(value = "orderId",required = true)String orderId);
}
