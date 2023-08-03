package com.yh.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.model.wemedia.pojos.WmNews;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WmNewsMapper  extends BaseMapper<WmNews> {
    
}