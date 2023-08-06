package com.yh.article.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.article.mapper.ApArticleConfigMapper;
import com.yh.article.mapper.ApArticleContentMapper;
import com.yh.article.mapper.ApArticleMapper;
import com.yh.article.service.ApArticleService;
import com.yh.article.service.ArticleFreemarkerService;
import com.yh.common.constants.ArticleConstants;
import com.yh.model.article.dtos.ArticleDto;
import com.yh.model.article.dtos.ArticleHomeDto;
import com.yh.model.article.pojos.ApArticle;
import com.yh.model.article.pojos.ApArticleConfig;
import com.yh.model.article.pojos.ApArticleContent;
import com.yh.model.common.dtos.ResponseResult;
import com.yh.model.common.enums.AppHttpCodeEnum;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper , ApArticle> implements ApArticleService {

    @Autowired
    private ApArticleMapper apArticleMapper;

    private final static short MAX_PAGE_SIZE = 50;
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //1.校验参数
        //分页条数的校验
        Integer size = dto.getSize();
        if(size == 0 || size == null){
            size = 10;
        }
        //分页值不超过50
        size = Math.min(size, MAX_PAGE_SIZE);

        //校验参数
        if(!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE)&&!type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            type = ArticleConstants.LOADTYPE_LOAD_MORE;

        }
        //频道参数校验
        if(StringUtils.isBlank(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        //时间校验
        if(dto.getMaxBehotTime() == null) dto.setMaxBehotTime(new Date());
        if(dto.getMinBehotTime() == null) dto.setMinBehotTime(new Date());

        //2.查询
        List<ApArticle> articlelist = apArticleMapper.loadArticleList(dto, type);
        //3.返回
        return ResponseResult.okResult(articlelist);
    }

    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private ArticleFreemarkerService articleFreemarkerService;

    /**
     * 保存app端相关文章
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
       /* try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //1.检查参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApArticle apArticle = new ApArticle();
        BeanUtils.copyProperties(dto,apArticle);

        //2.判断是否存在id
        if(dto.getId() == null){
            //2.1 不存在id  保存  文章  文章配置  文章内容

            //保存文章
            save(apArticle);

            //保存配置
            ApArticleConfig apArticleConfig = new ApArticleConfig(apArticle.getId());
            apArticleConfigMapper.insert(apArticleConfig);

            //保存 文章内容
            ApArticleContent apArticleContent = new ApArticleContent();
            apArticleContent.setArticleId(apArticle.getId());
            apArticleContent.setContent(dto.getContent());
            apArticleContentMapper.insert(apArticleContent);

        }else {
            //2.2 存在id   修改  文章  文章内容

            //修改  文章
            updateById(apArticle);

            //修改文章内容
            ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, dto.getId()));
            apArticleContent.setContent(dto.getContent());
            apArticleContentMapper.updateById(apArticleContent);
        }

        //异步调用 生成静态文件上传到minio中
        articleFreemarkerService.buildArticleToMinIO(apArticle,dto.getContent());

        //3.结果返回  文章的id
        return ResponseResult.okResult(apArticle.getId());
    }
}
