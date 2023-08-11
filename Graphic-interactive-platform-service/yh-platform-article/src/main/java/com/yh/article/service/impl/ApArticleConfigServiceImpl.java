package com.yh.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.article.mapper.ApArticleConfigMapper;
import com.yh.article.service.ApArticleConfigService;
import com.yh.common.constants.WmNewsMessageConstants;
import com.yh.model.article.pojos.ApArticleConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class ApArticleConfigServiceImpl extends ServiceImpl<ApArticleConfigMapper, ApArticleConfig> implements ApArticleConfigService {


    /**
     * 修改文章配置
     * @param map
     */
    @Override
    public void updateByMapUpOrDown(Map map) {
        //0 下架 1 上架
        Object enable = map.get("enable");
        boolean isDown = true;
        if(enable.equals(1)){
            isDown = false;
        }
        //修改文章配置
        update(Wrappers.<ApArticleConfig>lambdaUpdate().eq(ApArticleConfig::getArticleId,map.get("articleId")).set(ApArticleConfig::getIsDown,isDown));

    }

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Override
    public void updateByIdDelNews(String id) {
        //0未删除1删除
        boolean isDelete = true;
        //修改文章配置
        update(Wrappers.<ApArticleConfig>lambdaUpdate().eq(ApArticleConfig::getArticleId,id).set(ApArticleConfig::getIsDelete,isDelete));
        kafkaTemplate.send(WmNewsMessageConstants.WM_NEWS_LOAD_TOPIC, JSON.toJSONString(id));
    }
}