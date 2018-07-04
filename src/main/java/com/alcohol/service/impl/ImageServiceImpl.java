package com.alcohol.service.impl;

import com.alcohol.mapper.ImageMapper;
import com.alcohol.pojo.Image;
import com.alcohol.service.ImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageMapper imageMapper;
    @Override
    public List<Image> list() {
        return imageMapper.list();
    }
}
