package com.chill.chillbbs.service.util;

/**
 * @author Jarviz
 */
public class Constants {
    /**
     * 交换机名称
     */
    public final static String EXCHANGE_NAME = "exchange.datasync";

    /**
     * 增加话题评论数量队列名称
     */
    public final static String INCREASE_POST_COMMENT_NUMBER_QUEUE = "queue.postCommentNumber.increase";
    /**
     * 增加话题评论数量队列路由键
     */
    public final static String INCREASE_POST_COMMENT_NUMBER_KEY = "increasePostCommentNumber";
    /**
     * 减少话题评论数量队列名称
     */
    public final static String DECREASE_POST_COMMENT_NUMBER_QUEUE = "queue.postCommentNumber.decrease";
    /**
     * 减少话题评论数量队列路由键
     */
    public final static String DECREASE_POST_COMMENT_NUMBER_KEY = "decreasePostCommentNumber";
    /**
     * 增加话题收藏数量队列名称
     */
    public final static String INCREASE_POST_COLLECT_NUMBER_QUEUE = "queue.postCollectNumber.increase";
    /**
     * 增加话题收藏数量队列路由键
     */
    public final static String INCREASE_POST_COLLECT_NUMBER_KEY = "increasePostCollectNumber";
    /**
     * 减少话题收藏数量队列名称
     */
    public final static String DECREASE_POST_COLLECT_NUMBER_QUEUE = "queue.postCollectNumber.decrease";
    /**
     * 减少话题收藏数量队列路由键
     */
    public final static String DECREASE_POST_COLLECT_NUMBER_KEY = "decreasePostCollectNumber";
    /**
     * 增加话题喜爱数量队列名称
     */
    public final static String INCREASE_POST_LIKE_NUMBER_QUEUE = "queue.postLikeNumber.increase";
    /**
     * 增加话题喜爱数量队列路由键
     */
    public final static String INCREASE_POST_LIKE_NUMBER_KEY = "increasePostLikeNumber";
    /**
     * 减少话题喜爱数量队列名称
     */
    public final static String DECREASE_POST_LIKE_NUMBER_QUEUE = "queue.postLikeNumber.decrease";
    /**
     * 减少话题喜爱数量队列路由键
     */
    public final static String DECREASE_POST_LIKE_NUMBER_KEY = "decreasePostLikeNumber";



    /**
     * 删除话题评论队列名称
     */
    public final static String DELETE_POST_COMMENT_QUEUE = "queue.postComment.delete";
    /**
     * 删除话题评论路由键
     */
    public final static String DELETE_POST_COMMENT_KEY = "deletePostComment";
    /**
     * 删除话题收藏信息队列名称
     */
    public final static String DELETE_POST_COLLECTION_QUEUE = "queue.postCollection.delete";
    /**
     * 删除话题收藏信息路由键
     */
    public final static String DELETE_POST_COLLECTION_KEY = "deletePostCollection";
    /**
     * 删除话题喜爱信息队列名称
     */
    public final static String DELETE_POST_LIKED_QUEUE = "queue.postLiked.delete";
    /**
     * 删除话题喜爱信息路由键
     */
    public final static String DELETE_POST_LIKED_KEY = "deletePostLiked";
    /**
     * 删除话题附件队列名称
     */
    public final static String DELETE_POST_FILE_QUEUE = "queue.postFile.delete";
    /**
     * 删除话题附件路由键
     */
    public final static String DELETE_POST_FILE_KEY = "deletePostFile";


    /**
     * 删除话题评论回复队列名称
     */
    public final static String DELETE_POST_COMMENT_REPLY_QUEUE = "queue.postCommentReply.delete";
    /**
     * 删除话题评论回复路由键
     */
    public final static String DELETE_POST_COMMENT_REPLY_KEY = "deletePostCommentReply";

    /**
     * 增加专辑评论数量队列名称
     */
    public final static String INCREASE_ALBUM_COMMENT_NUMBER_QUEUE = "queue.albumCommentNumber.increase";
    /**
     * 增加专辑评论数量队列路由键
     */
    public final static String INCREASE_ALBUM_COMMENT_NUMBER_KEY = "increaseAlbumCommentNumber";
    /**
     * 减少专辑评论数量队列名称
     */
    public final static String DECREASE_ALBUM_COMMENT_NUMBER_QUEUE = "queue.albumCommentNumber.decrease";
    /**
     * 减少专辑评论数量队列路由键
     */
    public final static String DECREASE_ALBUM_COMMENT_NUMBER_KEY = "decreaseAlbumCommentNumber";
    /**
     * 增加专辑收藏数量队列名称
     */
    public final static String INCREASE_ALBUM_COLLECT_NUMBER_QUEUE = "queue.albumCollectNumber.increase";
    /**
     * 增加专辑收藏数量队列路由键
     */
    public final static String INCREASE_ALBUM_COLLECT_NUMBER_KEY = "increaseAlbumCollectNumber";
    /**
     * 减少专辑收藏数量队列名称
     */
    public final static String DECREASE_ALBUM_COLLECT_NUMBER_QUEUE = "queue.albumCollectNumber.decrease";
    /**
     * 减少专辑收藏数量队列路由键
     */
    public final static String DECREASE_ALBUM_COLLECT_NUMBER_KEY = "decreaseAlbumCollectNumber";

    /**
     * 删除专辑评论队列名称
     */
    public final static String DELETE_ALBUM_COMMENT_QUEUE = "queue.albumComment.delete";
    /**
     * 删除专辑评论路由键
     */
    public final static String DELETE_ALBUM_COMMENT_KEY = "deleteAlbumComment";
    /**
     * 删除专辑收藏信息队列名称
     */
    public final static String DELETE_ALBUM_COLLECTION_QUEUE = "queue.albumCollection.delete";
    /**
     * 删除专辑收藏信息路由键
     */
    public final static String DELETE_ALBUM_COLLECTION_KEY = "deleteAlbumCollection";
    /**
     * 删除专辑评论回复队列名称
     */
    public final static String DELETE_ALBUM_COMMENT_REPLY_QUEUE = "queue.albumCommentReply.delete";
    /**
     * 删除专辑评论回复路由键
     */
    public final static String DELETE_ALBUM_COMMENT_REPLY_KEY = "deleteAlbumCommentReplyReply";


    /**
     * 消费者数量
     */
    public final static int DEFAULT_CONCURRENT = 10;
    /**
     * 每个消费者获取的最大投递量
     */
    public final static int DEFAULT_PREFETCH_COUNT = 50;

}
