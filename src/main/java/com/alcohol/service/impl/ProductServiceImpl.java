package com.alcohol.service.impl;

import com.alcohol.mapper.ProductMapper;
import com.alcohol.pojo.Categorythree;
import com.alcohol.pojo.Product;
import com.alcohol.pojo.Sku;
import com.alcohol.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl  implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> getProductByCategorythreeId(Integer categorythreeId) {
        return productMapper.getProductByCategorythreeId(categorythreeId);
    }

    @Override
    public int addProduct(Product p) {
        return productMapper.addProduct(p);
    }

    @Override
    /**
     * 根据编号获得一条数据
     * @param id
     * @return
     */
    public Product getProductbyId(Integer productid){
        return productMapper.getProductbyId(productid);
    }

    @Override
    public List<Product> selTypeVlue() {
        return productMapper.selTypeVlue();
    }

    @Override
    public List<Product> selAllDESC(Product product,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return productMapper.selAllDESC(product);
    }

    @Override
    public List<Product> selAll(Product product,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,true);
        return productMapper.selAll(product);
    }

    @Override
    public List<Product> getProductByCategory(Map<String, Object> map) {
        return productMapper.getProductByCategory(map);
    }
    /**
     * 赵俊峰
     * 根据skuid和商品id查询商品，sku为主表，所以返回类型为sku
     * @return Sku
     */
    @Override
    public Sku selectProductBySkuIdAndProductId(Long productId, Long skuId) {
        Sku sku=null;
        for (Sku s:productMapper.selectProductBySkuIdAndProductId(null,skuId)) {
            sku=s;
        }
        return sku;
    }

    /**
     * 根据名字查询分类集合
     * @param categorythree_name  搜索栏中的值
     * @return 分类集合
     */
    @Override
    public List<Categorythree> getCategorythree(String categorythree_name) {
        return productMapper.getCategorythree(categorythree_name);
    }

    /**
     * 根据名字查询商品集合
     * @param product_name   搜索栏中的值
     * @return  商品集合
     */
    @Override
    public List<Product> getProductList(String product_name,int judge) {
        return productMapper.getProductList(product_name,judge);
    }

    /**
     * 根据分类属性值的id查询商品  xcf
     * @param typevalueArray  分类属性值
     * @param judge  排序类型
     * @return  商品集合
     */
    @Override
    public List<Product> getTypeProductList(int categoryThree,String[] typevalueArray, int judge) {
        return productMapper.getTypeProductList(categoryThree,typevalueArray,judge);
    }

    /**
     * 张鹏后台商品列表
     */
    public List<Product> listAll(String product_name,int status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        return productMapper.listAll(product_name,status);
    }
    /***
     *商品下架
     */
    public boolean updateStatus(int[] attr) {
        return productMapper.updateStatus(attr);
    }
    /***
     *商品上架
     */
    public boolean updateStatussj(int[] attr) {
        return productMapper.updateStatussj(attr);
    }

    /***
     * 删除商品
     * @param attr
     * @return
     */
    public boolean deleStatus(int[] attr) {
        return productMapper.deleStatus(attr);
    }
}
