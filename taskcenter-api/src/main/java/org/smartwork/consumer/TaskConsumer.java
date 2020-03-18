package org.smartwork.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGTaskOrderService;
import org.smartwork.service.IMchApiNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/****
 * 任务消费
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "spring.kafka.bootstrap-servers")
public class TaskConsumer {

    @Autowired
    IZGTaskOrderService  taskOrderService;
    @Autowired
    IMchApiNotifyService mchApiNotifyService;

    @KafkaListener(topics="topicTask")
    public void sendMsg(ConsumerRecord consumerRecord) {
        Optional<ConsumerRecord> kfkaMsg =  Optional.ofNullable(consumerRecord);
        if(kfkaMsg.isPresent()){
            ConsumerRecord obj  = kfkaMsg.get();
            String receJson = obj.value().toString();
            log.error("====支付接收参数===="+receJson);
            Map<String,Object> receMap = JSON.parseObject(receJson,Map.class);
            String mchOrderNo = receMap.get("mchOrderNo").toString();
            String payOrderId = receMap.get("payOrderId").toString();
            taskOrderService.modifyOrderStatus(mchOrderNo);
            Result<Object>  result = mchApiNotifyService.notifySuccess(payOrderId);
            log.error("====回写返回结果===="+JSON.toJSONString(result));
        } else {
            log.error("============="+JSON.toJSONString(consumerRecord));
        }
    }
}
