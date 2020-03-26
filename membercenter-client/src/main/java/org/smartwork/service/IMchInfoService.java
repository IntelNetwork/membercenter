package org.smartwork.service;

import org.forbes.comm.vo.Result;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@ConditionalOnProperty(name = "spring.application.paycenter")
@FeignClient(name = "mchInfoService",url="http://${spring.application.paycenter}",path = "/api/v1.0/transfer")
public interface IMchInfoService {


    /***
     * 获取商家信息
     * @param channel
     * @return
     */
    @RequestMapping(value = "/channel-mch-info",method = RequestMethod.GET)
    public Result<Map<String,Object>> channelMchInfo(@RequestParam(value = "channel",required = true)String channel);
}
