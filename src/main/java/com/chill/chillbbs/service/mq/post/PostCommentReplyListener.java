package com.chill.chillbbs.service.mq.post;

import com.chill.chillbbs.service.post.PostCommentReplyService;
import com.chill.chillbbs.util.Constants;
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
public class PostCommentReplyListener {
    @Resource
    PostCommentReplyService postCommentReplyService;

    @RabbitListener(queues = {Constants.DELETE_POST_COMMENT_REPLY_QUEUE})
    public void deleteReplyListener(Long commentId, Channel channel,
                                    @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            log.info("删除回复收到参数：{}", commentId);
            postCommentReplyService.deleteAllByCommentId(commentId);
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
