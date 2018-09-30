package org.sczs.auction.service;

import org.sczs.auction.dao.AuctionRecordMapper;
import org.sczs.auction.domain.AuctionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AuctionRecordService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class AuctionRecordService {
    /*
     * 自定义扩展
     */

    @Autowired
    private AuctionRecordMapper auctionRecordMapper;

    public List<AuctionRecord> queryAllAuctionRecord() {
        // 查询所有拍卖物品信息和竞拍信息
        return auctionRecordMapper.selectAll();
    }

    public AuctionRecord queryOneAuctionRecord(Integer id) {
        //查询单挑竞拍记录的详细
        return auctionRecordMapper.selectByPrimaryKey(id);
    }

    public AuctionRecord queryAuctionRecordByAuctionId(Integer id) {
        //根据拍卖id查询竞拍记录的详细
        return auctionRecordMapper.selectByAuctionIdAndStatus(id);
    }

    public Integer updateOneActionRecord(AuctionRecord auctionRecord) {
        // 更新竞拍信息
        return auctionRecordMapper.updateByPrimaryKey(auctionRecord);
    }

    public Integer deleteOneActionRecord(Integer id) {
        // 删除竞拍信息
        return auctionRecordMapper.deleteByPrimaryKey(id);
    }

    public Integer insertOneAuctionRecord(AuctionRecord auctionRecord) {
        // 插入竞拍信息
        return auctionRecordMapper.insert(auctionRecord);
    }

    public List<AuctionRecord> queryAuctionRecordByPayUserId(Integer id) {
        // 根据用户ID查询有关的所有竞拍记录
        return auctionRecordMapper.selectByPayUserId(id);
    }

    /**
    *@Description: 根据product_id查询auctionRecord
    *@create: 2018/9/13
    *@Author: SLJ
    *@Param:
    *@return:
    */
    public List<AuctionRecord> queryAuctionRecordByProductId(Integer productId){
        return auctionRecordMapper.queryAuctionRecordByProductId(productId);
    }
}
