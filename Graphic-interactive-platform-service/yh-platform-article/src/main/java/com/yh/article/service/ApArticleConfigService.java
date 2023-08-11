package com.yh.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.article.pojos.ApArticleConfig;

import java.util.Map;

public interface ApArticleConfigService extends IService<ApArticleConfig> {

    /**
     * 修改文章配置
     * @param map
     */
    public void updateByMapUpOrDown(Map map);


    void updateByIdDelNews(String id);
}