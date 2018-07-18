package com.alcohol.service.impl;

import com.alcohol.mapper.CategoryTwoMapper;
import com.alcohol.pojo.Categorytwo;
import com.alcohol.service.CategoryTwoService;
import com.github.pagehelper.PageHelper;
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
    public List<Categorytwo> getCategoryTwoInfo(Categorytwo ct) {
        try {
            return cm.getCategoryTwoInfo(ct);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Categorytwo> getCategoryTwoInfofy(Categorytwo ct,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true,true);
        List<Categorytwo> nws=cm.getCategoryTwoInfofy(ct);
        return nws;
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
    public int delistwo(Categorytwo two) {
        return cm.delistwo(two);
    }

    @Override
    public int delCategorytwo(Categorytwo two) {
        try {
            return cm.delCategorytwo(two);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int seltwoisthree(Integer parent) {
        return cm.seltwoisthree(parent);
    }
}
