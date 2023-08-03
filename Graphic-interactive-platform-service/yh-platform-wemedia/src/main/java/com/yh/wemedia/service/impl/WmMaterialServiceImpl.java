package com.yh.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.file.service.FileStorageService;
import com.yh.model.common.dtos.PageResponseResult;
import com.yh.model.common.dtos.ResponseResult;
import com.yh.model.common.enums.AppHttpCodeEnum;
import com.yh.model.wemedia.dtos.WmMaterialDto;
import com.yh.model.wemedia.pojos.WmMaterial;
import com.yh.utils.thread.WmThreadLocalUtil;
import com.yh.wemedia.mapper.WmMaterialMapper;
import com.yh.wemedia.service.WmMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;


@Slf4j
@Service
@Transactional
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {


    @Autowired
    private FileStorageService fileStorageService;
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {

        //1.检查参数
        if(multipartFile == null || multipartFile.getSize() == 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.上传图片到MinIO中
        String filename = UUID.randomUUID().toString().replace("-", "");

        String originalFilename = multipartFile.getOriginalFilename();

        String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileId = null;

        try {
            fileId = fileStorageService.uploadImgFile("", filename + postfix, multipartFile.getInputStream());
            log.info("上传文件成功 fileId:{}",fileId);
        }catch (Exception e){
            log.error("上传文件失败");
        }

        //3.图片保存到数据库
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setUserId(WmThreadLocalUtil.getUser().getId());
        wmMaterial.setUrl(fileId);
        wmMaterial.setIsCollection((short)0);
        wmMaterial.setType((short)0);
        wmMaterial.setCreatedTime(new Date());
        save(wmMaterial);
        //4.返回结果
        return ResponseResult.okResult(wmMaterial);
    }

    public ResponseResult findList( WmMaterialDto dto){

        //1.检查参数
        dto.checkParam();

        //2.分页查询
        IPage page = new Page(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<WmMaterial> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //是否收藏
        if(dto.getIsCollection() != null && dto.getIsCollection() == 1){
            lambdaQueryWrapper.eq(WmMaterial::getIsCollection,dto.getIsCollection());
        }

        //按照用户查询
        lambdaQueryWrapper.eq(WmMaterial::getUserId, WmThreadLocalUtil.getUser().getId());

        //按照时间倒序
        lambdaQueryWrapper.orderByDesc(WmMaterial::getCreatedTime);


        page = page(page,lambdaQueryWrapper);

        //3.结果返回
        ResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }
}
