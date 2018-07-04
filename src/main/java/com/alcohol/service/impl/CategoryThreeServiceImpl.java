package com.alcohol.service.impl;

import com.alcohol.mapper.CategoryThreeMapper;
import com.alcohol.pojo.Categorythree;
import com.alcohol.service.CategoryThreeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("categoryThreeService")
@Transactional
public class CategoryThreeServiceImpl implements CategoryThreeService {

    @Resource
    private CategoryThreeMapper cm;

    @Override
    public List<Categorythree> getCategoryThreeInfo(Map<String, Object> map) {
        try {
            return cm.getCategoryThreeInfo(map);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int getCategoryThree(String categorythreeId) {
        try {
            return cm.getCategoryThree(categorythreeId);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int addCategoryThree(Categorythree ct) {
        try {
            return cm.addCategoryThree(ct);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int updateCategorythree(Categorythree ct) {
        try {
            return cm.updateCategorythree(ct);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int delCategorythree(Long categorythree_id) {
        try {
            return cm.delCategorythree(categorythree_id);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }
}
