package org.sczs.auction.dao;

import org.sczs.auction.domain.AuctionInfo;

import java.util.List;

public interface AuctionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuctionInfo record);

    AuctionInfo selectByPrimaryKey(Integer id);

    List<AuctionInfo> selectAll();

    int updateByPrimaryKey(AuctionInfo record);

    AuctionInfo selectByProductIdAndStatus(Integer id);

    //根据productId查询auction_info
    AuctionInfo getAuctionInfoByProductId(Integer productId);

    Integer getAuctionInfoCount();
}