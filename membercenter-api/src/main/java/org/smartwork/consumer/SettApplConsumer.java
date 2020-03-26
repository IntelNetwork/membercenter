package org.smartwork.consumer;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.forbes.comm.enums.DeadUnitEnum;
import org.forbes.comm.enums.YesNoEnum;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.smartwork.biz.service.IZGSettApplService;
import org.smartwork.dal.entity.ZGMemberLevelOrder;
import org.smartwork.service.IMchApiNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/***体现申请
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
public class SettApplConsumer {


    @Autowired
    IMchApiNotifyService mchApiNotifyService;
    @Autowired
    IZGSettApplService settApplService;


    /***
     *转账回写方法
     * @param consumerRecord
     */
    @KafkaListener(topics="topicTransfer")
    public void receMsg(ConsumerRecord consumerRecord) {
        Optional<ConsumerRecord> kfkaMsg =  Optional.ofNullable(consumerRecord);
        if(kfkaMsg.isPresent()){
            ConsumerRecord obj  = kfkaMsg.get();
            String receJson = obj.value().toString();
            log.error("====支付接收参数===="+receJson);
            Map<String,Object> receMap = JSON.parseObject(receJson,Map.class);
            String mchOrderNo = receMap.get("mchOrderNo").toString();
            String payOrderId = receMap.get("payOrderId").toString();
            settApplService.transferSuccess(mchOrderNo);
            Result<Object> result = mchApiNotifyService.notifySuccess(payOrderId);
            log.error("====回写返回结果===="+JSON.toJSONString(result));
        } else {
            log.error("============="+JSON.toJSONString(consumerRecord));
        }
    }
}
