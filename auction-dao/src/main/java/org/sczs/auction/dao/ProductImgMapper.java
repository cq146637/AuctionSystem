package org.sczs.auction.dao;

import org.sczs.auction.domain.ProductImg;

import java.util.List;

public interface ProductImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductImg record);

    ProductImg selectByPrimaryKey(Integer id);

    List<ProductImg> selectAll();

    int updateByPrimaryKey(ProductImg record);

    ProductImg selectByProductIdAndImgType(Integer id);

    //根据product_id 查询product_img
    List<ProductImg> selectByProductId(Integer productId);
}