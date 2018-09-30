package org.sczs.auction.dao;

import org.sczs.auction.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    List<Product> queryProductBySaleUserId(Integer id);

    int queryProductIdByCondition(Product record);

    List<Product> queryProductByCondition(Map<String,Object> map);
}