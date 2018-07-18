package com.alcohol.mapper;

import com.alcohol.pojo.Categorythree;
import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品接口
 */
public interface ProductMapper {

    /**
     * 看了又看
     * @param categorythreeId
     * @return
     */
    public List<Product> getProductByCategorythreeId(Integer categorythreeId);

    /**
     * 新增商品
     * @param p
     * @return
     */
    public int addProduct(Product p);

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
     * 获取多个selTypeVlueId
     * @return
     */
    public List<Product> selTypeVlue();

    /**
     * 获取商品数据
     * @return
     */
    public List<Product> selAllDESC(Product product);

    /**
     * 模糊查倒叙
     * @param product
     * @return
     */
    public List<Product> selAll(Product product);

    /**
     * 查询首页的商品 李清
     * @return
     */
    public List<Product> getProductByCategory(Map<String,Object> map);
    /**
     * 赵俊峰
     * 根据skuid和商品id查询商品，sku为主表，所以返回类型为sku集合
     * @return List<Sku>
     */
    public List<Sku> selectProductBySkuIdAndProductId(@Param("productId")Long productId, @Param("skuId")Long skuId);


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
    public List<Product> getProductList(@Param("product_name")String product_name,@Param("judge")int judge);

    /**
     * 根据分类属性值的id查询商品   xcf
     * @param typevalueArray  分类属性值
     * @param judge  排序类型
     * @return  商品集合
     */
    public List<Product> getTypeProductList(@Param("categoryThree")int categoryThree,@Param("typevalueArray")String[] typevalueArray,@Param("judge")int judge);

    /**
     * 张鹏后台商品列表
     */
    public List<Product> listAll(@Param("product_name")String product_name,@Param("status") int status);
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
}
