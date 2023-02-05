package com.chill.chillbbs.service.mq.post;

import com.chill.chillbbs.service.post.PostService;
import com.chill.chillbbs.service.util.Constants;
import com.rabbitmq.client.Channel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jarviz
 */
@Component
@Slf4j
public class PostListener {
    @Resource
    PostService postService;

    @RabbitListener(queues = {Constants.INCREASE_POST_COMMENT_NUMBER_QUEUE})
    public void increaseCommentListener(Long id, Channel channel,
                                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            log.info("增加话题评论收到参数：{}", id);
            postService.increaseComment(id);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            try {
                //nack返回false，重新返回队列
                channel.basicNack(tag, false, true);
            } catch (IOException exception) {
                log.error("返回队列失败：{}", exception.getMessage());
            }
            log.error("同步失败：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {Constants.DECREASE_POST_COMMENT_NUMBER_QUEUE})
    public void decreaseCommentListener(Long id, Channel channel,
                                        @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            log.info("减少话题评论收到参数：{}", id);
            postService.decreaseComment(id);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            try {
                //nack返回false，重新返回队列
                channel.basicNack(tag, false, true);
            } catch (IOException exception) {
                log.error("返回队列失败：{}", exception.getMessage());
            }
            log.error("同步失败：{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
