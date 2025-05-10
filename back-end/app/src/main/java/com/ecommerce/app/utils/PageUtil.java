package com.ecommerce.app.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtil {
    private final static int PAGE_SIZE = 20;
    private final static int FIRST_PAGE = 1;

    public static Pageable getPageable(Integer page, Integer size) {
        if (page == null || page <= 0) {
            page = FIRST_PAGE;
        }
        if (size == null || size <= 0) {
            size = PAGE_SIZE;
        }
        return PageRequest.of(page - 1, size);
    }
}
