package org.sczs.auction.dao;

import org.sczs.auction.domain.OrderInfo;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    List<OrderInfo> selectAll();

    int updateByPrimaryKey(OrderInfo record);

    int getOrderInfoCount();

    List<OrderInfo> queryOrderInfoByAuctionRecordId(Integer id);
}