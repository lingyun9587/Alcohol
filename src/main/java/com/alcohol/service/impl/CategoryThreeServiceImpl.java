package com.alcohol.service.impl;

import com.alcohol.mapper.CategoryThreeMapper;
import com.alcohol.pojo.Categorythree;
import com.alcohol.service.CategoryThreeService;
import com.github.pagehelper.PageHelper;
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
    public Categorythree getCategorythreeById(Integer id) {
        return cm.getCategorythreeById(id);
    }

    @Override
    public List<Categorythree> getCategoryThreeInfo(Categorythree three) {
        try {
            return cm.getCategoryThreeInfo(three);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Categorythree> getCategoryThreeInfofy(Categorythree three, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true,true);
        List<Categorythree> nws=cm.getCategoryThreeInfofy(three);
        return nws;
    }

    @Override
    public int getCategoryThree(Categorythree ct) {
        try {
            return cm.getCategoryThree(ct);
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
    public int delisthree(Categorythree three) {
        return cm.delisthree(three);
    }

    @Override
    public int delCategorythree(Categorythree three) {
        try {
            return cm.delCategorythree(three);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int selthreeisshu(Integer parent) {
        return cm.selthreeisshu(parent);
    }
}
