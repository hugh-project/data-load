package com.argo.service.dataload.region.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA
 * Date: 2019/4/22
 * Time: 17:07
 *
 * @author hugh
 */
@Configuration
public class AmqpConfiguration {
    public static final String DATA_CHANGE_QUEUE = "data-change1";

    @Bean(name = DATA_CHANGE_QUEUE)
    public Queue dataChangeQueue() {
        return new Queue(DATA_CHANGE_QUEUE);
    }
}
