package com.chill.chillbbs.service.mq;

import com.chill.chillbbs.service.util.Constants;
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
     * @return 增加话题收藏数量队列
     */
    @Bean
    public Queue increasePostCollectNumQueue() {
        return new Queue(Constants.INCREASE_POST_COLLECT_NUMBER_QUEUE);
    }

    /**
     * @return 增加话题收藏数量绑定
     */
    @Bean
    public Binding increasePostCollectNumBinding() {
        return BindingBuilder.bind(increasePostCollectNumQueue()).to(dataSyncExchange()).with(Constants.INCREASE_POST_COLLECT_NUMBER_KEY);
    }

    /**
     * @return 减少话题收藏数量队列
     */
    @Bean
    public Queue decreasePostCollectNumQueue() {
        return new Queue(Constants.DECREASE_POST_COLLECT_NUMBER_QUEUE);
    }

    /**
     * @return 减少话题收藏数量绑定
     */
    @Bean
    public Binding decreasePostCollectNumBinding() {
        return BindingBuilder.bind(decreasePostCollectNumQueue()).to(dataSyncExchange()).with(Constants.DECREASE_POST_COLLECT_NUMBER_KEY);
    }

    /**
     * @return 增加话题喜爱数量队列
     */
    @Bean
    public Queue increasePostLikeNumQueue() {
        return new Queue(Constants.INCREASE_POST_LIKE_NUMBER_QUEUE);
    }

    /**
     * @return 增加话题喜爱数量绑定
     */
    @Bean
    public Binding increasePostLikeNumBinding() {
        return BindingBuilder.bind(increasePostLikeNumQueue()).to(dataSyncExchange()).with(Constants.INCREASE_POST_LIKE_NUMBER_KEY);
    }

    /**
     * @return 减少话题喜爱数量队列
     */
    @Bean
    public Queue decreasePostLikeNumQueue() {
        return new Queue(Constants.DECREASE_POST_LIKE_NUMBER_QUEUE);
    }

    /**
     * @return 减少话题喜爱数量绑定
     */
    @Bean
    public Binding decreasePostLikeNumBinding() {
        return BindingBuilder.bind(decreasePostLikeNumQueue()).to(dataSyncExchange()).with(Constants.DECREASE_POST_LIKE_NUMBER_KEY);
    }

    /**
     * @return 增加话题评论喜爱数量队列
     */
    @Bean
    public Queue increasePostCommentLikeNumQueue() {
        return new Queue(Constants.INCREASE_POST_COMMENT_LIKE_NUMBER_QUEUE);
    }

    /**
     * @return 增加话题评论喜爱数量绑定
     */
    @Bean
    public Binding increasePostCommentLikeNumBinding() {
        return BindingBuilder.bind(increasePostCommentLikeNumQueue()).to(dataSyncExchange()).with(Constants.INCREASE_POST_COMMENT_LIKE_NUMBER_KEY);
    }

    /**
     * @return 减少话题评论喜爱数量队列
     */
    @Bean
    public Queue decreasePostCommentLikeNumQueue() {
        return new Queue(Constants.DECREASE_POST_COMMENT_LIKE_NUMBER_QUEUE);
    }

    /**
     * @return 减少话题评论喜爱数量绑定
     */
    @Bean
    public Binding decreasePostCommentLikeNumBinding() {
        return BindingBuilder.bind(decreasePostCommentLikeNumQueue()).to(dataSyncExchange()).with(Constants.DECREASE_POST_COMMENT_LIKE_NUMBER_KEY);
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
     * @return 增加专辑收藏数量队列
     */
    @Bean
    public Queue increaseAlbumCollectNumQueue() {
        return new Queue(Constants.INCREASE_ALBUM_COLLECT_NUMBER_QUEUE);
    }

    /**
     * @return 增加专辑收藏数量绑定
     */
    @Bean
    public Binding increaseAlbumCollectNumBinding() {
        return BindingBuilder.bind(increaseAlbumCollectNumQueue()).to(dataSyncExchange()).with(Constants.INCREASE_ALBUM_COLLECT_NUMBER_KEY);
    }

    /**
     * @return 减少专辑收藏数量队列
     */
    @Bean
    public Queue decreaseAlbumCollectNumQueue() {
        return new Queue(Constants.DECREASE_ALBUM_COLLECT_NUMBER_QUEUE);
    }

    /**
     * @return 减少专辑收藏数量绑定
     */
    @Bean
    public Binding decreaseAlbumCollectNumBinding() {
        return BindingBuilder.bind(decreaseAlbumCollectNumQueue()).to(dataSyncExchange()).with(Constants.DECREASE_ALBUM_COLLECT_NUMBER_KEY);
    }

    /**
     * @return 增加专辑评论喜爱数量队列
     */
    @Bean
    public Queue increaseAlbumCommentLikeNumQueue() {
        return new Queue(Constants.INCREASE_ALBUM_COMMENT_LIKE_NUMBER_QUEUE);
    }

    /**
     * @return 增加专辑评论喜爱数量绑定
     */
    @Bean
    public Binding increaseAlbumCommentLikeNumBinding() {
        return BindingBuilder.bind(increaseAlbumCommentLikeNumQueue()).to(dataSyncExchange()).with(Constants.INCREASE_ALBUM_COMMENT_LIKE_NUMBER_KEY);
    }

    /**
     * @return 减少专辑评论喜爱数量队列
     */
    @Bean
    public Queue decreaseAlbumCommentLikeNumQueue() {
        return new Queue(Constants.DECREASE_ALBUM_COMMENT_LIKE_NUMBER_QUEUE);
    }

    /**
     * @return 减少专辑评论喜爱数量绑定
     */
    @Bean
    public Binding decreaseAlbumCommentLikeNumBinding() {
        return BindingBuilder.bind(decreaseAlbumCommentLikeNumQueue()).to(dataSyncExchange()).with(Constants.DECREASE_ALBUM_COMMENT_LIKE_NUMBER_KEY);
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
     * @return 删除话题收藏信息队列
     */
    @Bean
    public Queue deletePostCollectionQueue() {
        return new Queue(Constants.DELETE_POST_COLLECTION_QUEUE);
    }

    /**
     * @return 删除话题收藏信息绑定
     */
    @Bean
    public Binding deletePostCollectionBinding() {
        return BindingBuilder.bind(deletePostCollectionQueue()).to(dataSyncExchange()).with(Constants.DELETE_POST_COLLECTION_KEY);
    }

    /**
     * @return 删除话题喜爱信息队列
     */
    @Bean
    public Queue deletePostLikedQueue() {
        return new Queue(Constants.DELETE_POST_LIKED_QUEUE);
    }

    /**
     * @return 删除话题喜爱信息绑定
     */
    @Bean
    public Binding deletePostLikedBinding() {
        return BindingBuilder.bind(deletePostLikedQueue()).to(dataSyncExchange()).with(Constants.DELETE_POST_LIKED_KEY);
    }

    /**
     * @return 删除话题附件队列
     */
    @Bean
    public Queue deletePostFileQueue() {
        return new Queue(Constants.DELETE_POST_FILE_QUEUE);
    }

    /**
     * @return 删除话题附件绑定
     */
    @Bean
    public Binding deletePostFileBinding() {
        return BindingBuilder.bind(deletePostFileQueue()).to(dataSyncExchange()).with(Constants.DELETE_POST_FILE_KEY);
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
     * @return 删除话题评论喜爱信息绑定
     */
    @Bean
    public Queue deletePostCommentLikedQueue() {
        return new Queue(Constants.DELETE_POST_COMMENT_LIKED_QUEUE);
    }

    /**
     * @return 删除话题评论喜爱信息绑定
     */
    @Bean
    public Binding deletePostCommentLikedBinding() {
        return BindingBuilder.bind(deletePostCommentLikedQueue()).to(dataSyncExchange()).with(Constants.DELETE_POST_COMMENT_LIKED_KEY);
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
     * @return 删除专辑收藏信息队列
     */
    @Bean
    public Queue deleteAlbumCollectionQueue() {
        return new Queue(Constants.DELETE_ALBUM_COLLECTION_QUEUE);
    }

    /**
     * @return 删除专辑收藏信息绑定
     */
    @Bean
    public Binding deleteAlbumCollectionBinding() {
        return BindingBuilder.bind(deletePostCollectionQueue()).to(dataSyncExchange()).with(Constants.DELETE_ALBUM_COLLECTION_KEY);
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
    /**
     * @return 删除专辑评论喜爱信息绑定
     */
    @Bean
    public Queue deleteAlbumCommentLikedQueue() {
        return new Queue(Constants.DELETE_ALBUM_COMMENT_LIKED_QUEUE);
    }

    /**
     * @return 删除专辑评论喜爱信息绑定
     */
    @Bean
    public Binding deleteAlbumCommentLikedBinding() {
        return BindingBuilder.bind(deleteAlbumCommentLikedQueue()).to(dataSyncExchange()).with(Constants.DELETE_ALBUM_COMMENT_LIKED_KEY);
    }

//    @Bean
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
}
