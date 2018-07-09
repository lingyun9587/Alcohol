package com.alcohol.service.impl;

import com.alcohol.dto.CommodityExecution;
import com.alcohol.enums.CommodityEnum;
import com.alcohol.exceptions.CommodityOperationException;
import com.alcohol.mapper.CommodityMapper;
import com.alcohol.mapper.OrderMapper;
import com.alcohol.pojo.Commodity;
import com.alcohol.service.CommodityService;
import com.alcohol.vo.OrderstatusVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;
    @Resource
    private OrderMapper orderMapper;
    @Override
    public List<OrderstatusVo> listVoByUserId(Integer id) {
        return commodityMapper.listVoByUserId(id);
    }

    @Override
    public List<Commodity> listCommodityInfo(Integer id,Integer status) {
        return commodityMapper.listCommodityInfo(id,status);
    }

    @Override
    public CommodityExecution removeId(Long orderId, Long commodityId) throws CommodityOperationException {

        int result = 0;
        CommodityExecution commodityExecution = null;
        try{
            result = commodityMapper.removeById(commodityId);
            if(result >0){
                result = commodityMapper.selectByOrderId(orderId);
                if(result>0){
                    commodityExecution = new CommodityExecution(CommodityEnum.SUCCESS);
                    result = orderMapper.removeOrderInfo(orderId);
                    if(result >0){
                        commodityExecution = new CommodityExecution(CommodityEnum.SUCCESS);
                    }else{
                        commodityExecution = new CommodityExecution(CommodityEnum.NOFAIL);
                    }
                }else{
                    commodityExecution = new CommodityExecution(CommodityEnum.SUCCESS);
                }
            }else{
                commodityExecution = new CommodityExecution(CommodityEnum.NOFAIL);
            }
        }catch (CommodityOperationException e){
            throw  new CommodityOperationException(e.toString());
        }
        return commodityExecution;
    }
}
