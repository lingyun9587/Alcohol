package com.alcohol.mapper;

import com.alcohol.pojo.Commodity;
import com.alcohol.pojo.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuMapper {

    /**
     * 根据sku查询库存
     * @param id
     * @return
     */
    int getSkuStockById(Integer id);

    /**
     * 根据sku编号修改数量
     * @param skuId
     * @param number
     * @param  status 0锁定 1 解锁
     * @return
     */
    int updateInfo(@Param("skuId") Long skuId,@Param("number") Integer number,@Param("status") Integer status);

    /**
     * 根据sku查询信息
     * @param id
     * @return
     */
    Sku getById(Long id);

    /**
     * 根据集合查询信息
     * @param list
     * @return
     */
    List<Sku>  listById(List<Commodity> list);


}
