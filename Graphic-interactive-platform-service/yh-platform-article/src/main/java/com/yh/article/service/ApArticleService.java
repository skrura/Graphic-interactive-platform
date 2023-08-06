package com.yh.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.article.dtos.ArticleDto;
import com.yh.model.article.dtos.ArticleHomeDto;
import com.yh.model.article.pojos.ApArticle;
import com.yh.model.common.dtos.ResponseResult;
import org.springframework.stereotype.Service;


@Service
public interface ApArticleService extends IService<ApArticle> {
     /**
      * 加载文章列表
      * @param dto
      * @param type
      * @return
      */
     ResponseResult load(ArticleHomeDto dto,Short type);
     /**
      * 保存app端相关文章
      * @param dto
      * @return
      */
     ResponseResult saveArticle(ArticleDto dto) ;

}
