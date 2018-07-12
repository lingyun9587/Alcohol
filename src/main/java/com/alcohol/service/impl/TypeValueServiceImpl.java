package com.alcohol.service.impl;

import com.alcohol.mapper.TypeValueMapper;
import com.alcohol.pojo.Typevalue;
import com.alcohol.service.TypeValueService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("typeValueService")
@Transactional
public class TypeValueServiceImpl implements TypeValueService {

    @Resource
    private TypeValueMapper tm;


    @Override
    public Typevalue selIdType(Long typeValueId) {
        try {
            return tm.selIdType(typeValueId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public List<Typevalue> getTypeValue(Map<String, Object> map) {
        try {
            return tm.getTypeValue(map);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Typevalue> getTypeValuefy(Typevalue tv,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true,true);
        List<Typevalue> nws=tm.getTypeValuefy(tv);
        return nws;
    }

    @Override
    public int addTypeValue(Typevalue tn) {
        try {
            return tm.addTypeValue(tn);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int updissel(Typevalue tv) {
        return tm.updissel(tv);
    }

    @Override
    public int modifyTypeValue(Typevalue tn) {
        try {
            return tm.modifyTypeValue(tn);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int delisshop(Typevalue tv) {
        return tm.delisshop(tv);
    }

    @Override
    public int delTypeValue(Typevalue tv) {
        try {
            return tm.delTypeValue(tv);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }
}
