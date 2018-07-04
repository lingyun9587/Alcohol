package com.alcohol.service.impl;

import com.alcohol.mapper.TypeValueMapper;
import com.alcohol.pojo.Typevalue;
import com.alcohol.service.TypeValueService;
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
    public List<Typevalue> getTypeValue(Map<String, Object> map) {
        try {
            return tm.getTypeValue(map);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
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
    public int modifyTypeValue(Typevalue tn) {
        try {
            return tm.modifyTypeValue(tn);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int delTypeValue(Long typeValueId) {
        try {
            return tm.delTypeValue(typeValueId);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }
}
