package com.alcohol.config;

import com.alcohol.pojo.FileUploadProperteis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private FileUploadProperteis fileUploadProperteis;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(fileUploadProperteis.getStaticProductPath()).addResourceLocations("file:" + fileUploadProperteis.getUploadProduct() + "/");
        registry.addResourceHandler(fileUploadProperteis.getStaticAccessPath()).addResourceLocations("file:" + fileUploadProperteis.getUploadFolder() + "/");
      registry.addResourceHandler(fileUploadProperteis.getStaticAccessPath()).addResourceLocations("file:" + fileUploadProperteis.getUploadComment() + "/");

    }
}
