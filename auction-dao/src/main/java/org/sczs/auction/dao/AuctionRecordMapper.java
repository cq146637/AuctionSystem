package org.sczs.auction.dao;

import org.sczs.auction.domain.AuctionRecord;

import java.util.List;

public interface AuctionRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuctionRecord record);

    AuctionRecord selectByPrimaryKey(Integer id);

    List<AuctionRecord> selectAll();

    int updateByPrimaryKey(AuctionRecord record);

    AuctionRecord selectByAuctionIdAndStatus(Integer id);

    //根据product_id查询auctionRecord
    List<AuctionRecord> queryAuctionRecordByProductId(Integer productId);

    List<AuctionRecord> selectByPayUserId(Integer id);
}