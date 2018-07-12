package com.alcohol.service.impl;

import com.alcohol.mapper.RktableMapper;
import com.alcohol.pojo.Rktable;
import com.alcohol.service.RktableService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RktableServiceImpl implements RktableService {
    @Resource
    private RktableMapper rktableMapper;
    @Override
    public List<Rktable> SelAll(String productName,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return rktableMapper.SelAll(productName);
    }
}
