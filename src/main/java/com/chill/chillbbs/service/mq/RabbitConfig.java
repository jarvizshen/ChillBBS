package com.chill.chillbbs.service.mq;

import com.chill.chillbbs.util.Constants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置监听
 *
 * @author Jarviz
 */
@Slf4j
@Configuration
public class RabbitConfig {
    @Resource
    ConnectionFactory connectionFactory;
    @Resource
    SimpleRabbitListenerContainerFactoryConfigurer configurer;

    @Bean
    public RabbitTemplate createRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        /*
         * 当mandatory标志位设置为true时
         * 如果exchange根据自身类型和消息routingKey无法找到一个合适的queue存储消息
         * 那么broker会调用basic.return方法将消息返还给生产者
         * 当mandatory设置为false时，出现上述情况broker会直接将消息丢弃
         */
        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
            log.info("ConfirmCallback: 相关数据：" + correlationData);
            log.info("ConfirmCallback: 确认情况：" + b);
            log.info("ConfirmCallback: 原因：" + s);
        });
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.info("ReturnCallback: 消息：" + returnedMessage.getMessage());
            log.info("ReturnCallback: 回应码：" + returnedMessage.getReplyCode());
            log.info("ReturnCallback: 回应信息：" + returnedMessage.getReplyText());
            log.info("ReturnCallback: 交换机：" + returnedMessage.getExchange());
            log.info("ReturnCallback: 路由键：" + returnedMessage.getRoutingKey());
        });
        return rabbitTemplate;
    }

    @Bean("pointTaskContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(Constants.DEFAULT_PREFETCH_COUNT);
        factory.setConcurrentConsumers(Constants.DEFAULT_CONCURRENT);
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
