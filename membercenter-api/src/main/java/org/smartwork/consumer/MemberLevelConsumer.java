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
import org.smartwork.biz.service.IZGMemberLevelOrderService;
import org.smartwork.dal.entity.ZGMemberLevelOrder;
import org.smartwork.service.IMchApiNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
public class MemberLevelConsumer {


    @Autowired
    IMchApiNotifyService mchApiNotifyService;
    @Autowired
    IZGMemberLevelOrderService memberLevelOrderService;

    @KafkaListener(topics="topicMemberlevel")
    public void sendMsg(ConsumerRecord consumerRecord) {
        Optional<ConsumerRecord> kfkaMsg =  Optional.ofNullable(consumerRecord);
        if(kfkaMsg.isPresent()){
            ConsumerRecord obj  = kfkaMsg.get();
            String receJson = obj.value().toString();
            log.error("====支付接收参数===="+receJson);
            Map<String,Object> receMap = JSON.parseObject(receJson,Map.class);
            String mchOrderNo = receMap.get("mchOrderNo").toString();
            String payOrderId = receMap.get("payOrderId").toString();
            ZGMemberLevelOrder memberLevelOrder = memberLevelOrderService.getOne(new QueryWrapper<ZGMemberLevelOrder>()
                    .eq("ml_order_no",mchOrderNo));
            if(ConvertUtils.isNotEmpty(memberLevelOrder)
                    && YesNoEnum.NO.getCode().equalsIgnoreCase(String.valueOf(memberLevelOrder.getTakeEffect()))){
                Date currentDate = new Date();
                Date endDate = null;
                String deadUnit = memberLevelOrder.getDeadUnit();
                int deadLine = memberLevelOrder.getDeadline();
                if(DeadUnitEnum.MONTH.equals(deadUnit)){
                    endDate  = DateUtils.addMonths(currentDate,deadLine);
                }
                if(DeadUnitEnum.QUARTER.equals(deadUnit)){
                    endDate  = DateUtils.addMonths(currentDate,deadLine*3);
                }
                if(DeadUnitEnum.YEAR.equals(deadUnit)){
                    endDate  = DateUtils.addYears(currentDate,deadLine);
                }
                memberLevelOrderService.update(new UpdateWrapper<ZGMemberLevelOrder>()
                        .set("start_date",currentDate)
                        .set("end_date",endDate)
                        .set("take_effect",YesNoEnum.YES.getCode())
                        .eq("ml_order_no",mchOrderNo)));
            }
            Result<Object> result = mchApiNotifyService.notifySuccess(payOrderId);
            log.error("====回写返回结果===="+JSON.toJSONString(result));
        } else {
            log.error("============="+JSON.toJSONString(consumerRecord));
        }
    }
}
