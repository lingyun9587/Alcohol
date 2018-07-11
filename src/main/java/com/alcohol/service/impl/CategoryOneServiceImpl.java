package com.alcohol.service.impl;

import com.alcohol.mapper.CategoryOneMapper;
import com.alcohol.pojo.Categoryone;
import com.alcohol.service.CategoryOneService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryOneService")
@Transactional
public class CategoryOneServiceImpl implements CategoryOneService {

    @Resource
    private CategoryOneMapper cm;

    @Override
    public List<Categoryone> getCategoryOneInfo() {
        try {
            return cm.getCategoryOneInfo();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int getCategoryone(String categoryontName) {
        try {
            return cm.getCategoryone(categoryontName);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int addCategoryOne(Categoryone co) {
        try {
            return cm.addCategoryOne(co);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int updateCategoryone(Categoryone co) {
        try {
            return cm.updateCategoryone(co);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int delCategoryone(Long categoryone_id) {
        try {
            return cm.delCategoryone(categoryone_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Categoryone> getCategoryOneInfofy(Integer pageNum,Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize,true,true);
        List<Categoryone> news=cm.getCategoryOneInfofy();
        return news;
    }
}
