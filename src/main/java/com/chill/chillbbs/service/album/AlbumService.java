package com.chill.chillbbs.service.album;

import com.chill.chillbbs.entity.album.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Jarviz
 */
public interface AlbumService {
    /**
     * 返回所有专辑分页结果
     *
     * @param pageable 分页参数
     * @return 所有专辑
     */
    @Async("chillPool")
    CompletableFuture<Page<Album>> allAlbumsPage(Pageable pageable);

    /**
     * 返回所有专辑
     *
     * @return 所有专辑
     */
    @Async("chillPool")
    CompletableFuture<List<Album>> allAlbums();

    /**
     * 模糊检索专辑
     *
     * @param albumName 专辑名称
     * @return 匹配专辑
     */
    @Async("chillPool")
    CompletableFuture<List<Album>> findAllByAlbumName(String albumName);

    /**
     * 添加或更新专辑
     *
     * @param album 专辑实体
     * @return 是否添加或更新成功
     */
    @Async("chillPool")
    CompletableFuture<Album> saveOrUpdate(Album album);

    /**
     * 模糊检索
     *
     * @param albumName 专辑名称
     * @param pageable  分页参数
     * @return 匹配结果
     */
    CompletableFuture<Page<Album>> findAllByAlbumNamePage(String albumName, Pageable pageable);

    /**
     * 根据id删除专辑
     *
     * @param id id
     * @return 是否删除成功
     */
    @Async("chillPool")
    CompletableFuture<Boolean> deleteAlbumById(long id);

    /**
     * 根据id获得对应专辑
     *
     * @param id 专辑id
     * @return 专辑帖子
     */
    @Async("chillPool")
    CompletableFuture<Optional<Album>> getById(Long id);

    /**
     * 评论数量加一
     *
     * @param albumId 专辑id
     */
    void increaseComment(Long albumId);

    /**
     * 评论数量减一
     *
     * @param albumId 专辑id
     */
    void decreaseComment(Long albumId);
}
