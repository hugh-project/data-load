package com.argo.service.dataload.region;

import com.ARGO.common.model.LogBean;
import com.ARGO.common.utils.LogHelper;
import com.ARGO.log.Logger;
import com.ARGO.log.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.argo.service.dataload.region.config.AmqpConfiguration;
import com.argo.service.dataload.region.domain.DataChangeEvent;
import com.argo.service.dataload.region.domain.Region;
import com.argo.service.dataload.region.domain.RegionChangeEvent;
import com.argo.service.dataload.region.domain.RegionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegionMessageHandler {
    private static final String regionLoadQueue = "data-load-region";
    private Logger logger;
    private final LoggerFactory loggerFactory;
@Autowired
private RegionRepository regionRepository;
@Autowired
RabbitTemplate template;
    @Autowired
    public RegionMessageHandler(LoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
    }

    @Autowired
    public Logger getLogger() {
        return logger = loggerFactory.getLogger(RegionMessageHandler.class);
    }


    @RabbitListener(queues = regionLoadQueue,containerFactory = "pointTaskContainerFactory")
    public void receiveString(String msg) {
        RegionChangeEvent regionChangeEvent = JSON.parseObject(msg, RegionChangeEvent.class);
        List<String> stringList = new ArrayList<>();
        switch (regionChangeEvent.getOperation()){
            case Delete:;break;
            case Update:;break;
            case Add:addRegion(regionChangeEvent.getRegions(),stringList);break;
        }
        if(stringList.size()>0){
            DataChangeEvent dataChangeEvent = new DataChangeEvent();
            dataChangeEvent.setItems(stringList);
            dataChangeEvent.setKey(regionChangeEvent.getKey());
            dataChangeEvent.setEntity("Region");
            dataChangeEvent.setCategory("master");
            dataChangeEvent.setDomain("Region");
            template.convertAndSend(AmqpConfiguration.DATA_CHANGE_QUEUE,JSON.toJSONString(dataChangeEvent));
        }
    }
    private void addRegion(List<Region> regionList,List<String> ids){
        if(regionList.size()>0){
            for (Region region:regionList){
                regionRepository.save(region);
                ids.add(region.getRegionId());
            }

        }
    }
//    /**
//     * 发送消息
//     * @param event 数据
//     * @param logBean 日志
//     * @throws JsonProcessingException JsonProcessingException
//     */
//    public void notifyChanges(DataChangeEvent event, LogBean logBean) throws JsonProcessingException {
//        logBean.setMq(AmqpConfiguration.DATA_CHANGE_QUEUE);
//        logBean.setType("MQ");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(event);
//        this.template.convertAndSend(AmqpConfiguration.DATA_CHANGE_QUEUE, json);
//        logger.systemLog(LogHelper.Info(logBean,"send to {%s} message", AmqpConfiguration.DATA_CHANGE_QUEUE));
//    }

}
