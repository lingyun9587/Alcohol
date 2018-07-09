package com.alcohol.mapper;

import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品接口
 */
public interface ProductMapper {

    /**
     * 根据编号获得一条数据
     * @param id
     * @return
     */
    public Product getProductbyId(Integer productid);


    /**
     * 根据搜索商品名搜索商品
     * @return
     */
    public List<Product>  getProduct();

    /**
     * 赵俊峰
     * 根据skuid和商品id查询商品，sku为主表，所以返回类型为sku集合
     * @return List<Sku>
     */
    public List<Sku> selectProductBySkuIdAndProductId(@Param("productId")Long productId,@Param("skuId")Long skuId);
}
