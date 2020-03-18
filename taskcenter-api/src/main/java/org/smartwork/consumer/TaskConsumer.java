package org.smartwork.consumer;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.utils.ConvertUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/****
 * 任务消费
 */
@Slf4j
@Component
public class TaskConsumer {


    @KafkaListener(topics="topicTask")
    public void sendMsg(ConsumerRecord consumerRecord) {
        Optional<ConsumerRecord> kfkaMsg =  Optional.ofNullable(consumerRecord);
        if(kfkaMsg.isPresent()){
            ConsumerRecord obj  = kfkaMsg.get();
            String receJson = obj.value().toString();
            log.error("====短信接收参数===="+receJson);
            Map<String,Object> receMap = JSON.parseObject(receJson,Map.class);
            String busCode = receMap.get("busCode").toString();
            String mobile = receMap.get("mobile").toString();
            String content = receMap.get("content").toString();
            String  msgId = receMap.get("msgId").toString();
            String resultCode = "";
            String resultMsg = "";
            ZGMsgTemplate msgTemplate = msgTemplateService.getOne(new QueryWrapper<ZGMsgTemplate>().eq("msg_type", MsgTypeEnum.SMS.getCode())
                    .eq("bus_code",busCode));
            if(ConvertUtils.isNotEmpty(msgTemplate)){
                String templateId =  msgTemplate.getTemplateId();
                if(ConvertUtils.isNotEmpty(templateId)){
                    Map<String,String> resultMap =  SMSUtils.SMSBuild.build(new CryunSmsStrategy())
                            .setMobiles(mobile.split(","))
                            .setTemplateId(templateId)
                            .setContent(content)
                            .sendSMS();
                    resultCode = resultMap.get("resultCode");
                    resultMsg = resultMap.get("resultMsg");
                } else {
                    String templateContent =  msgTemplate.getContent();
//*自定义消息模板*

                    if(ConvertUtils.isNotEmpty(templateContent)){
                        content = String.format(templateContent,content.split(","));
                        Map<String,String> resultMap =  SMSUtils
                                .SMSBuild.build(new YmrtSmsStrategy())
                                .setMobiles(mobile.split(","))
                                .setContent(content)
                                .sendSMS();
                        resultCode = resultMap.get("resultCode");
                        resultMsg = resultMap.get("resultMsg");
                        log.error("=====1111==="+JSON.toJSONString(resultMap));
                    }
                }
                this.addMsgLog(resultCode,resultMsg,msgId,content,msgTemplate);
            } else {
                log.error("{}=====暂无对应消息模板",busCode);
            }
        } else {
            log.error("============="+JSON.toJSONString(consumerRecord));
        }
    }
}
