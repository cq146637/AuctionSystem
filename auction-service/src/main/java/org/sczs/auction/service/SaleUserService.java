package org.sczs.auction.service;

import org.sczs.auction.dao.SaleUserMapper;
import org.sczs.auction.domain.SaleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SaleUserService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class SaleUserService {
    /*
     * 自定义扩展
     */
    @Autowired
    private SaleUserMapper saleUserMapper;


    public List<SaleUser> selectAllSaleUser() {
        // 查询所有的拍卖者信息
        return saleUserMapper.selectAll();
    }

    public SaleUser queryOneSaleUser(Integer id) {
        // 查询单条拍卖者信息
        return saleUserMapper.selectByPrimaryKey(id);
    }

    public SaleUser getSaleUserByProductId(Integer productId){
        return saleUserMapper.getSaleUserByProductId(productId);
    }

    public Integer getSaleUserCount() {
        // 查询所有拍卖者数量
        return saleUserMapper.getSaleUserCount();
    }

    /*根据条件查询saleUser*/
    public SaleUser getSaleUserByCondition(SaleUser saleUser){
        return saleUserMapper.getSaleUserByCondition(saleUser);
    }

    /*注册saleUser*/
    public int insert(SaleUser record){
        return saleUserMapper.insert(record);
    }
}
