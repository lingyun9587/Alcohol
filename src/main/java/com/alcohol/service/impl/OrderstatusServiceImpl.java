package com.alcohol.service.impl;

import com.alcohol.mapper.OrderstatusMapper;
import com.alcohol.service.OrderstatusService;
import com.alcohol.vo.OrderstatusVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderstatusServiceImpl implements OrderstatusService {

    @Resource
    private OrderstatusMapper orderstatusMapper;
    @Override
    public List<OrderstatusVo> listByUserId(Long userId) {
        return orderstatusMapper.listByUserId(userId);
    }
}
