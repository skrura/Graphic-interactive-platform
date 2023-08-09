package com.yh.search.service;

import com.yh.model.search.dtos.UserSearchDto;
import com.yh.model.common.dtos.ResponseResult;

import java.io.IOException;

public interface ArticleSearchService {

    /**
     ES文章分页搜索
     @return
     */
    ResponseResult search(UserSearchDto userSearchDto) throws IOException;
}