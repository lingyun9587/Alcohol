package com.alcohol.service.impl;

import com.alcohol.mapper.TypeNameMapper;
import com.alcohol.pojo.TypeName;
import com.alcohol.service.TypeNameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Service("typeNameService")
public class TypeNameServiceImpl implements TypeNameService {

    @Resource
    private TypeNameMapper tm;

    @Override
    public List<TypeName> getTypeName(Map<String, Object> map) {
        try {
            return tm.getTypeName(map);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }
}
