package com.alcohol.service.impl;

import com.alcohol.mapper.ImageMapper;
import com.alcohol.pojo.Image;
import com.alcohol.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class ImageServiceImpl  implements ImageService {
    @Resource
    private ImageMapper imageMapper;
    @Override
    public List<Image> list() {
        return imageMapper.list();
    }

    @Override
    public Image selImageByProductId(Long productId) {
        List<Image> imgs=imageMapper.selImageByProductId(productId);
        Image img=null;
        if(imgs!=null){
            for (Image im:imgs) {
                img=im;
                return img;
            }
        }
        return img;
    }

    @Override
    public List<Image> selProductId(Long productId) {
        return imageMapper.selImageByProductId(productId);
    }

    @Override
    public int addImage(Image image) {
        return imageMapper.addImage(image);
    }
}
