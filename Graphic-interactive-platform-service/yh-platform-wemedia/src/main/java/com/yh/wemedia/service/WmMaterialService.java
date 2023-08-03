package com.yh.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.model.common.dtos.ResponseResult;
import com.yh.model.wemedia.dtos.WmMaterialDto;
import com.yh.model.wemedia.pojos.WmMaterial;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

public interface WmMaterialService extends IService<WmMaterial> {

    //图片上传
    public  ResponseResult uploadPicture(MultipartFile multipartFile);

    //素材列表查询
    public ResponseResult findList(@RequestBody WmMaterialDto wmMaterialDto);
}