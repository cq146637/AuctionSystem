package org.sczs.auction.service;

import org.sczs.auction.dao.AuctionInfoShowMapper;
import org.sczs.auction.domain.AuctionRecord;
import org.sczs.auction.dto.AuctionInfoShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionInfoShowService {
    @Autowired
    AuctionInfoShowMapper auctionInfoShowMapper;
    @Autowired
    AuctionRecordService auctionRecordService;

    public List<AuctionInfoShow> selectShowingAll(){
        List<AuctionInfoShow> auctionInfoShows = auctionInfoShowMapper.selectShowingAll();
        for (AuctionInfoShow auctionInfoShow:auctionInfoShows
             ) {
            List<AuctionRecord> auctionRecordList = auctionRecordService.queryAuctionRecordByProductId(auctionInfoShow.getProductId());
            if (auctionRecordList.size() != 0){
                auctionInfoShow.setPrice(auctionRecordList.get(0).getPrice());
            }else {
                auctionInfoShow.setPrice(0);
            }
        }
        return auctionInfoShows;
    }

    public List<AuctionInfoShow> selectPreShowAll(){
        return auctionInfoShowMapper.selectPreShowAll();
    }

    public List<AuctionInfoShow> selectAllByCategory(String categoryName){
        List<AuctionInfoShow> auctionInfoShows = auctionInfoShowMapper.selectAllByCategory(categoryName);
        for (AuctionInfoShow auctionInfoShow:auctionInfoShows) {
            List<AuctionRecord> auctionRecordList = auctionRecordService.queryAuctionRecordByProductId(auctionInfoShow.getProductId());
            if (auctionRecordList.size() != 0){
                auctionInfoShow.setPrice(auctionRecordList.get(0).getPrice());
            }else {
                auctionInfoShow.setPrice(0);
            }
        }
        return auctionInfoShows;
    }

    public AuctionInfoShow searchProductByCondition(Integer productId){
        return auctionInfoShowMapper.searchProductByCondition(productId);
    }
}
