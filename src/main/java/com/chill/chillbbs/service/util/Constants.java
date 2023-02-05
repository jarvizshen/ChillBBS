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
     * 添加es话题队列名称
     */
    public final static String ADD_POST_DOC_QUEUE = "queue.postDoc.add";
    /**
     * 添加es话题队列路由键
     */
    public final static String ADD_POST_DOC_KEY = "addPostDoc";
    /**
     * 删除es话题队列名称
     */
    public final static String DELETE_POST_DOC_QUEUE = "queue.postDoc.delete";
    /**
     * 删除es话题路由键
     */
    public final static String DELETE_POST_DOC_KEY = "deletePostDoc";


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
     * 改变es话题收藏状态队列名称
     */
    public final static String POST_DOC_COLLECT_QUEUE = "queue.postDoc.collect";
    /**
     * 改变es话题收藏状态路由键
     */
    public final static String POST_DOC_COLLECT_KEY = "collectPostDoc";

    /**
     * 增加es话题评论数量队列名称
     */
    public final static String INCREASE_POST_DOC_COMMENT_NUMBER_QUEUE = "queue.postDocCommentNumber.increase";
    /**
     * 增加es话题评论数量队列路由键
     */
    public final static String INCREASE_POST_DOC_COMMENT_NUMBER_KEY = "increasePostDocCommentNumber";
    /**
     * 减少es话题评论数量队列名称
     */
    public final static String DECREASE_POST_DOC_COMMENT_NUMBER_QUEUE = "queue.postDocCommentNumber.decrease";
    /**
     * 减少es话题评论数量队列路由键
     */
    public final static String DECREASE_POST_DOC_COMMENT_NUMBER_KEY = "decreasePostDocCommentNumber";


    /**
     * 删除话题评论队列名称
     */
    public final static String DELETE_POST_COMMENT_QUEUE = "queue.postComment.delete";
    /**
     * 删除话题评论路由键
     */
    public final static String DELETE_POST_COMMENT_KEY = "deletePostComment";
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
     * 删除专辑评论队列名称
     */
    public final static String DELETE_ALBUM_COMMENT_QUEUE = "queue.albumComment.delete";
    /**
     * 删除专辑评论路由键
     */
    public final static String DELETE_ALBUM_COMMENT_KEY = "deleteAlbumComment";


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
