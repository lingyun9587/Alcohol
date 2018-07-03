package com.alcohol.service.impl;

import com.alcohol.mapper.CategoryTwoMapper;
import com.alcohol.pojo.Categorytwo;
import com.alcohol.service.CategoryTwoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("categoryTwoService")
@Transactional
public class CategoryTwoServiceImpl implements CategoryTwoService {

    @Resource
    private CategoryTwoMapper cm;

    @Override
    public List<Categorytwo> getCategoryTwoInfo(Map<String, Object> map) {
        try {
            return cm.getCategoryTwoInfo(map);
        }catch (RuntimeException e){     
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int getCategoryTwo(String categorytwoName) {
        try {
            return cm.getCategoryTwo(categorytwoName);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int addCategoryTwo(Categorytwo ct) {
        try {
            return cm.addCategoryTwo(ct);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int updateCategorytwo(Categorytwo ct) {
        try {
            return cm.updateCategorytwo(ct);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int delCategorytwo(Long categorytwo_id) {
        try {
            return cm.delCategorytwo(categorytwo_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }
}
