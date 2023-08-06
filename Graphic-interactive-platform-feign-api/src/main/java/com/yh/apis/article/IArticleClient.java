package com.yh.apis.article;


import com.yh.model.article.dtos.ArticleDto;
import com.yh.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;

@FeignClient(value = "platform-article")
public interface IArticleClient {
        @PostMapping("/api/v1/article/save")
        public ResponseResult saveArticle(@RequestBody ArticleDto dto);
}
