package org.sczs.auction.dao;

import org.sczs.auction.domain.SaleUser;

import java.util.List;

public interface SaleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleUser record);

    SaleUser selectByPrimaryKey(Integer id);

    List<SaleUser> selectAll();

    int updateByPrimaryKey(SaleUser record);

    //根据productId查询saleuser
    SaleUser getSaleUserByProductId(Integer productId);

    Integer getSaleUserCount();

    //根据条件查询saleUser
    SaleUser getSaleUserByCondition(SaleUser saleUser);
}