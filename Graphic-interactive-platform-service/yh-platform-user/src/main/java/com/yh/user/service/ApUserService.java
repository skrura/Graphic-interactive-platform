package com.yh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.common.dtos.ResponseResult;
import com.yh.model.user.dtos.LoginDto;
import com.yh.model.user.pojos.ApUser;

public interface ApUserService extends IService<ApUser> {

    public ResponseResult login(LoginDto dto);

}

