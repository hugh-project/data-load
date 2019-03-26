package com.argo.service.dataload.region;

import com.ARGO.common.model.LogBean;
import com.ARGO.common.utils.LogHelper;
import com.ARGO.log.Logger;
import com.ARGO.log.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionMessageHandler {
    private static final String regionLoadQueue = "data-load-regions";

    private Logger logger;
    private final LoggerFactory loggerFactory;

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
