package com.alcohol.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传相关属性
 **/
@Component
@ConfigurationProperties(prefix = "file")
@Data
public class FileUploadProperteis {


    //静态资源对外暴露的访问路径
    private String staticAccessPath;

    //静态资源对外暴露的访问路径商品图片
    private String staticProductPath;

    //文件上传目录商品图片
    private String uploadFolder ;

    private String uploadProduct;

    //评论
    private String uploadComment;

    private String staticCommentPath;

}
