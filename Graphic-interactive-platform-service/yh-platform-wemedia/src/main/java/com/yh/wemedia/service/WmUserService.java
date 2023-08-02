package com.yh.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.common.dtos.ResponseResult;
import com.yh.model.wemedia.dtos.WmLoginDto;
import com.yh.model.wemedia.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}