package com.chill.chillbbs.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Jarviz
 */
public class PageUtil {
    public static Pageable getPageable(PostOrderType orderType, PostOrderType ascOrDesc, Pageable pageable) {
        Sort sort;
        if (ascOrDesc.equals(PostOrderType.DESC)) {
            if (orderType.equals(PostOrderType.CREATE)) {
                sort = Sort.by(Sort.Direction.DESC, "id");
            } else {
                sort = Sort.by(Sort.Direction.DESC, "commentNum");
            }
        } else {
            if (orderType.equals(PostOrderType.CREATE)) {
                sort = Sort.by(Sort.Direction.ASC, "id");
            } else {
                sort = Sort.by(Sort.Direction.ASC, "commentNum");
            }
        }
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return pageable;
    }
}
