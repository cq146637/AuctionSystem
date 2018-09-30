package org.sczs.auction.service;

import org.sczs.auction.dao.ProductMapper;
import org.sczs.auction.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ProductService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class ProductService {
    /*
     * 自定义扩展
     */

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getProductsList() {
        // 查询所有拍卖物品信息
        return productMapper.selectAll();
    }

    public Product queryOneProduct(Integer id) {
        // 查询单条拍卖物品信息
        return productMapper.selectByPrimaryKey(id);
    }

    public Integer insertOneProduct(Product product) {
        // 添加拍卖物品信息
        return productMapper.insert(product);
    }

    public Integer updateOneProduct(Product product) {
        // 修改拍卖物品信息
        return productMapper.updateByPrimaryKey(product);
    }

    public Integer deleteOneProduct(Integer id) {
        // 删除拍卖物品信息
        return productMapper.deleteByPrimaryKey(id);
    }

    public List<Product> queryProductBySaleUserId(Integer id) {
        // 根据拍卖者ID查询所有的商品
        return productMapper.queryProductBySaleUserId(id);
    }

    public int queryProductIdByCondition(Product product) {
        // 根据商品名称和商品描述查询商品
        return productMapper.queryProductIdByCondition(product);
    }

    public List<Product> queryProductByCondition(Map<String,Object> map){
        return productMapper.queryProductByCondition(map);
    }

}
