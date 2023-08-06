package com.yh.wemedia.service;


import org.springframework.stereotype.Service;

public interface WmNewsAutoScanService {
    /**
     * 自媒体文章审核
     * @param id  自媒体文章id
     */
    public void autoScanWmNews(Integer id);
}
