package org.sczs.auction.service;

import org.sczs.auction.dao.ProductImgMapper;
import org.sczs.auction.domain.Product;
import org.sczs.auction.domain.ProductImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductImgService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class ProductImgService {
    /*
     * 自定义扩展
     */

    @Autowired
    private ProductImgMapper productImgMapper;

    public List<ProductImg> getProductImgList() {
        return productImgMapper.selectAll();
    }

    public Integer deleteOneProductImg(Integer id) {
        // 删除单条图片信息
        return productImgMapper.deleteByPrimaryKey(id);
    }

    public ProductImg getOneProductImg(Integer id) {
        // 查询单条图片信息
        return productImgMapper.selectByPrimaryKey(id);
    }

    public Integer updateOneProductImg(ProductImg productImg) {
        // 修改单条图片信息
        return productImgMapper.updateByPrimaryKey(productImg);
    }

    public Integer insertOneProductImg(ProductImg productImg) {
        // 插入单条图片信息
        return productImgMapper.insert(productImg);
    }

    public ProductImg getOneProductImgByProductId(Integer id) {
        // 根据商品ID查询商品图片信息，我们只要一张展示图
        return productImgMapper.selectByProductIdAndImgType(id);
    }

    /**
    *@Description: 根据product_id 查询product_img
    *@create: 2018/9/13
    *@Author: SLJ
    *@Param:
    *@return:
    */
        public List<ProductImg> selectByProductId(Integer productId) {
        return productImgMapper.selectByProductId(productId);
    }
}
