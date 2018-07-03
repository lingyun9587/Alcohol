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
}
