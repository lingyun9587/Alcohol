package com.alcohol.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传相关属性
 **/
@Component
@ConfigurationProperties(prefix = "file")
public class FileUploadProperteis {


    //静态资源对外暴露的访问路径
    private String staticAccessPath;

    //静态资源对外暴露的访问路径商品图片
    private String staticProductPath;

    //文件上传目录商品图片
    private String uploadFolder ;


    private String uploadProduct;

    public String getUploadComment() {
        return uploadComment;
    }

    public void setUploadComment(String uploadComment) {
        this.uploadComment = uploadComment;
    }

    //评论
    private String uploadComment;

    private String staticCommentPath;

    public void setStaticAccessPath(String staticAccessPath) {
        this.staticAccessPath = staticAccessPath;
    }

    public void setStaticProductPath(String staticProductPath) {
        this.staticProductPath = staticProductPath;
    }

    public void setUploadFolder(String uploadFolder) {
        this.uploadFolder = uploadFolder;
    }

    public void setUploadProduct(String uploadProduct) {
        this.uploadProduct = uploadProduct;
    }

    public String getStaticAccessPath() {
        return staticAccessPath;
    }

    public String getStaticProductPath() {
        return staticProductPath;
    }

    public String getUploadFolder() {
        return uploadFolder;
    }

    public String getUploadProduct() {
        return uploadProduct;
    }
}
