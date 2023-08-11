package com.yh.wemedia.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.common.dtos.ResponseResult;
import com.yh.model.wemedia.dtos.WmNewsDto;
import com.yh.model.wemedia.dtos.WmNewsPageReqDto;
import com.yh.model.wemedia.pojos.WmNews;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface WmNewsService extends IService<WmNews> {

    /**
     * 查询文章
     * @param dto
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto dto);
    public ResponseResult submitNews(WmNewsDto dto);
    /**
     * 文章的上下架
     * @param dto
     * @return
     */
    public ResponseResult downOrUp(WmNewsDto dto);
    /**
     * 文章的删除
     * @param
     * @return
     */
    public ResponseResult delNews(int id,HttpServletRequest request);

    void realdelnews(String id);
}