package com.yh.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.model.article.dtos.ArticleHomeDto;
import com.yh.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
    /*
    * 加载文章列表
    * type  1 加载更多 2 加载最新*/

     List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);

}