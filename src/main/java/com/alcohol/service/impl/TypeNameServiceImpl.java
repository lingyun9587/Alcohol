package com.alcohol.service.impl;

import com.alcohol.mapper.TypeNameMapper;
import com.alcohol.pojo.TypeName;
import com.alcohol.service.TypeNameService;
import com.github.pagehelper.PageHelper;
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

    @Override
    public List<TypeName> getTypeNamefy(TypeName tn,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,true,true);
        List<TypeName> news=tm.getTypeNamefy(tn);
        return news;
    }

    @Override
    public int addTypeName(TypeName tn) {
        try {
            return tm.addTypeName(tn);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int modifyTypeName(TypeName tn) {
        try {
            return tm.modifyTypeName(tn);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int delistypevalue(TypeName tn) {
        return tm.delistypevalue(tn);
    }

    @Override
    public int TypeNameissel(TypeName tn) {
        return tm.TypeNameissel(tn);
    }

    @Override
    public int delTypeName(TypeName tn) {
        try {
            return tm.delTypeName(tn);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public int seltnId(TypeName typeName) {
        return tm.seltnId(typeName);
    }

    @Override
    public int list() {
        return tm.list();
    }
}
