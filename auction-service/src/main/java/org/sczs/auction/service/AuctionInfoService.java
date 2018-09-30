package org.sczs.auction.service;

import org.sczs.auction.dao.AuctionInfoMapper;
import org.sczs.auction.domain.AuctionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AuctionInfoService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class AuctionInfoService{
    /*
     * 自定义扩展
     */

    @Autowired
    private AuctionInfoMapper auctionInfoMapper;

    // 查询所有拍卖物品信息
    public List<AuctionInfo> queryAllAuctionInfo() {
        return auctionInfoMapper.selectAll();
    }

    // 查询单条拍卖的详细信息
    public AuctionInfo queryOneAuctionInfo(Integer id) {
        return auctionInfoMapper.selectByPrimaryKey(id);
    }

    public AuctionInfo queryAuctionInfoByProductId(Integer id) {
        // 根据商品id查询拍卖信息
        return auctionInfoMapper.selectByProductIdAndStatus(id);
    }

    // 插入拍卖信息
    public Integer insertOneAuctionInfo(AuctionInfo auctionInfo) {
        return auctionInfoMapper.insert(auctionInfo);
    }

    // 更新拍卖信息
    public Integer updateOneActionInfo(AuctionInfo auctionInfo) {
        return auctionInfoMapper.updateByPrimaryKey(auctionInfo);
    }

    // 删除拍卖信息
    public Integer deleteOneActionInfo(Integer id) {
        return auctionInfoMapper.deleteByPrimaryKey(id);
    }

    /**
    *@Description: 根据productId查询auctioninfo
    *@create: 2018/9/13
    *@Author: SLJ
    *@Param:
    *@return:
    */
    public AuctionInfo getAuctionInfoByProductId(Integer productId){
        return auctionInfoMapper.getAuctionInfoByProductId(productId);
    }

    public Integer getAuctionInfoCount() {
        // 获取所有的拍卖信息
        return auctionInfoMapper.getAuctionInfoCount();
    }

}
