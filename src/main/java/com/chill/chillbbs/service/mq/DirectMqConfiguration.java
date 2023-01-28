package com.chill.chillbbs.service.mq;

import com.chill.chillbbs.util.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jarviz
 */
@Configuration
public class DirectMqConfiguration {
//    @Resource
//    ObjectMapper objectMapper;

    /**
     * @return 交换机
     */
    @Bean
    public DirectExchange dataSyncExchange() {
        return new DirectExchange(Constants.EXCHANGE_NAME);
    }

    /**
     * @return 增加话题评论数量队列
     */
    @Bean
    public Queue increasePostCommentQueue() {
        return new Queue(Constants.INCREASE_POST_COMMENT_NUMBER_QUEUE);
    }

    /**
     * @return 增加话题评论数量绑定
     */
    @Bean
    public Binding increasePostCommentBinding() {
        return BindingBuilder.bind(increasePostCommentQueue()).to(dataSyncExchange()).with(Constants.INCREASE_POST_COMMENT_NUMBER_KEY);
    }

    /**
     * @return 减少话题评论数量队列
     */
    @Bean
    public Queue decreasePostCommentQueue() {
        return new Queue(Constants.DECREASE_POST_COMMENT_NUMBER_QUEUE);
    }

    /**
     * @return 减少话题评论数量绑定
     */
    @Bean
    public Binding decreasePostCommentBinding() {
        return BindingBuilder.bind(decreasePostCommentQueue()).to(dataSyncExchange()).with(Constants.DECREASE_POST_COMMENT_NUMBER_KEY);
    }

    /**
     * @return 增加es话题评论数量队列
     */
    @Bean
    public Queue increasePostDocCommentQueue() {
        return new Queue(Constants.INCREASE_POST_DOC_COMMENT_NUMBER_QUEUE);
    }

    /**
     * @return 增加es话题评论数量绑定
     */
    @Bean
    public Binding increasePostDocCommentBinding() {
        return BindingBuilder.bind(increasePostDocCommentQueue()).to(dataSyncExchange()).with(Constants.INCREASE_POST_DOC_COMMENT_NUMBER_KEY);
    }

    /**
     * @return 减少es话题评论数量队列
     */
    @Bean
    public Queue decreasePostDocCommentQueue() {
        return new Queue(Constants.DECREASE_POST_DOC_COMMENT_NUMBER_QUEUE);
    }

    /**
     * @return 减少es话题评论数量绑定
     */
    @Bean
    public Binding decreasePostDocCommentBinding() {
        return BindingBuilder.bind(decreasePostDocCommentQueue()).to(dataSyncExchange()).with(Constants.DECREASE_POST_DOC_COMMENT_NUMBER_KEY);
    }

    @Bean
    public Queue changePostDocCollectedQueue() {
        return new Queue(Constants.POST_DOC_COLLECT_QUEUE);
    }

    @Bean
    public Binding changePostDocCollectedBinding() {
        return BindingBuilder.bind(changePostDocCollectedQueue()).to(dataSyncExchange()).with(Constants.POST_DOC_COLLECT_KEY);
    }

    /**
     * @return 增加专辑评论数量队列
     */
    @Bean
    public Queue increaseAlbumCommentQueue() {
        return new Queue(Constants.INCREASE_ALBUM_COMMENT_NUMBER_QUEUE);
    }

    /**
     * @return 增加专辑评论数量绑定
     */
    @Bean
    public Binding increaseAlbumCommentBinding() {
        return BindingBuilder.bind(increaseAlbumCommentQueue()).to(dataSyncExchange()).with(Constants.INCREASE_ALBUM_COMMENT_NUMBER_KEY);
    }

    /**
     * @return 减少专辑评论数量队列
     */
    @Bean
    public Queue decreaseAlbumCommentQueue() {
        return new Queue(Constants.DECREASE_ALBUM_COMMENT_NUMBER_QUEUE);
    }

    /**
     * @return 减少专辑评论数量绑定
     */
    @Bean
    public Binding decreaseAlbumCommentBinding() {
        return BindingBuilder.bind(decreaseAlbumCommentQueue()).to(dataSyncExchange()).with(Constants.DECREASE_ALBUM_COMMENT_NUMBER_KEY);
    }

    /**
     * @return 添加es话题队列
     */
    @Bean
    public Queue addPostDocQueue() {
        return new Queue(Constants.ADD_POST_DOC_QUEUE);
    }

    /**
     * @return 添加es话题绑定
     */
    @Bean
    public Binding addPostDocBinding() {
        return BindingBuilder.bind(addPostDocQueue()).to(dataSyncExchange()).with(Constants.ADD_POST_DOC_KEY);
    }

    /**
     * @return 删除es话题队列
     */
    @Bean
    public Queue deletePostDocQueue() {
        return new Queue(Constants.DELETE_POST_DOC_QUEUE);
    }

    /**
     * @return 删除es话题绑定
     */
    @Bean
    public Binding deletePostDocBinding() {
        return BindingBuilder.bind(deletePostDocQueue()).to(dataSyncExchange()).with(Constants.DELETE_POST_DOC_KEY);
    }

    /**
     * @return 删除话题评论队列
     */
    @Bean
    public Queue deletePostCommentQueue() {
        return new Queue(Constants.DELETE_POST_COMMENT_QUEUE);
    }

    /**
     * @return 删除话题评论绑定
     */
    @Bean
    public Binding deletePostCommentBinding() {
        return BindingBuilder.bind(deletePostCommentQueue()).to(dataSyncExchange()).with(Constants.DELETE_POST_COMMENT_KEY);
    }

    /**
     * @return 删除话题评论回复绑定
     */
    @Bean
    public Queue deletePostCommentReplyQueue() {
        return new Queue(Constants.DELETE_POST_COMMENT_REPLY_QUEUE);
    }

    /**
     * @return 删除话题评论回复绑定
     */
    @Bean
    public Binding deletePostCommentReplyBinding() {
        return BindingBuilder.bind(deletePostCommentReplyQueue()).to(dataSyncExchange()).with(Constants.DELETE_POST_COMMENT_REPLY_KEY);
    }


    /**
     * @return 删除专辑评论队列
     */
    @Bean
    public Queue deleteAlbumCommentQueue() {
        return new Queue(Constants.DELETE_ALBUM_COMMENT_QUEUE);
    }

    /**
     * @return 删除专辑评论绑定
     */
    @Bean
    public Binding deleteAlbumCommentBinding() {
        return BindingBuilder.bind(deletePostCommentQueue()).to(dataSyncExchange()).with(Constants.DELETE_ALBUM_COMMENT_KEY);
    }

    /**
     * @return 删除专辑评论回复队列
     */
    @Bean
    public Queue deleteAlbumCommentReplyQueue() {
        return new Queue(Constants.DELETE_ALBUM_COMMENT_REPLY_QUEUE);
    }

    /**
     * @return 删除专辑评论回复绑定
     */
    @Bean
    public Binding deleteAlbumCommentReplyBinding() {
        return BindingBuilder.bind(deletePostCommentReplyQueue()).to(dataSyncExchange()).with(Constants.DELETE_ALBUM_COMMENT_REPLY_KEY);
    }

//    @Bean
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
}
