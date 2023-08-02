package com.yh.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.article.dtos.ArticleHomeDto;
import com.yh.model.article.pojos.ApArticle;
import com.yh.model.common.dtos.ResponseResult;
import org.springframework.stereotype.Service;


@Service
public interface ApArticleService extends IService<ApArticle> {
     ResponseResult load(ArticleHomeDto dto,Short type);

}
