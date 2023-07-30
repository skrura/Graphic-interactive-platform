package com.yh.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.article.dtos.ArticleHomeDto;
import com.yh.model.article.pojos.ApArticle;
import com.yh.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.ResponseBody;


public interface ApArticleService extends IService<ApArticle> {
    public ResponseResult load(ArticleHomeDto dto,Short type);

}
