package org.sczs.auction.dao;

import org.sczs.auction.domain.PayUser;

import java.util.List;

public interface PayUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PayUser record);

    PayUser selectByPrimaryKey(Integer id);

    List<PayUser> selectAll();

    int updateByPrimaryKey(PayUser record);

    PayUser getPayUserByCondition(PayUser payUser);

    Integer getPayUserCount();
}