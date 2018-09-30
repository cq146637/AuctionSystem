package org.sczs.auction.dao;

import org.sczs.auction.dto.AuctionInfoShow;

import java.util.List;

public interface AuctionInfoShowMapper {
    List<AuctionInfoShow> selectShowingAll();
    List<AuctionInfoShow> selectPreShowAll();
    List<AuctionInfoShow> selectAllByCategory(String categoryName);
    AuctionInfoShow searchProductByCondition(Integer productId);

}
