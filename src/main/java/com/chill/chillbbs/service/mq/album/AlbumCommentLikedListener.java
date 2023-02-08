package com.chill.chillbbs.service.mq.album;

import com.chill.chillbbs.service.album.AlbumCollectionService;
import com.chill.chillbbs.service.album.AlbumCommentLikedService;
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
public class AlbumCommentLikedListener {
    @Resource
    AlbumCommentLikedService albumCommentLikedService;

    @RabbitListener(queues = {Constants.DELETE_ALBUM_COMMENT_LIKED_QUEUE})
    public void deleteCollectionListener(Long id, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            log.info("删除专辑评论喜爱收到参数：{}", id);
            albumCommentLikedService.deleteAll(id);
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
