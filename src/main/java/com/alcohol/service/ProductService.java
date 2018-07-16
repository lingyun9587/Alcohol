package com.alcohol.service;

import com.alcohol.pojo.Categorythree;
import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductService {
    /**
     * 新增商品
     * @param p
     * @return
     */
    public int addProduct(Product p);

    /**
     * 根据编号获得一条数据
     * @param productid
     * @return
     */
    public Product getProductbyId(Integer productid);

    /**
     * 查询首页的商品
     * @return
     */
    public List<Product> getProductByCategory(Map<String,Object> map);
    /**
     * 获取多个selTypeVlueId
     * @return
     */
    public List<Product> selTypeVlue();
    /**
     * 获取商品数据
     * @return
     */
    public List<Product> selAllDESC(Product product,int pageNum, int pageSize);

    /**
     * 模糊查
     * @param product
     * @return
     */
    public List<Product> selAll(Product product,int pageNum, int pageSize);


    /**
     * 赵俊峰
     * 根据skuid和商品id查询商品，sku为主表，所以返回类型为sku
     * @return Sku
     */
    public Sku selectProductBySkuIdAndProductId(Long productId, Long skuId);

    /**
     * 根据分类名进行模糊查询三级分类  放到下拉框中   xcf
     * @param categorythree_name  搜索栏中的值
     * @return  三级分类集合
     */
    public List<Categorythree> getCategorythree(String categorythree_name);

    /**
     * 根据商品名进行模糊查询   xcf
     * @param product_name
     * @return
     */
    public List<Product> getProductList(String product_name,int judge);
    /**
     * 张鹏后台商品列表
     */
    public List<Product> listAll(@Param("product_name")String product_name, @Param("status") int status,int pageNum, int pageSize);
    /***
     *商品下架
     */
    public boolean updateStatus(int [] attr);
    /***
     *商品上架
     */
    public boolean updateStatussj(int [] attr);
    /***
     *商品删除
     */
    public boolean deleStatus(int [] attr);

    /**
     * 修改销量
     * @param productId
     * @param number
     * @return
     */
    int updatesales(@Param("productId") Long productId,@Param("number") Integer number);
}
