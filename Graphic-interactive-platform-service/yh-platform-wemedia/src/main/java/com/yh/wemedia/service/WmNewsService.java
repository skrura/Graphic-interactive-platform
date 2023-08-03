package com.yh.wemedia.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.common.dtos.ResponseResult;
import com.yh.model.wemedia.dtos.WmNewsDto;
import com.yh.model.wemedia.dtos.WmNewsPageReqDto;
import com.yh.model.wemedia.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {

    /**
     * 查询文章
     * @param dto
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto dto);
    public ResponseResult submitNews(WmNewsDto dto);
}