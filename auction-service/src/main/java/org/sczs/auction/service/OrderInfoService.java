package org.sczs.auction.service;

import org.sczs.auction.dao.OrderInfoMapper;
import org.sczs.auction.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OrderInfoService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class OrderInfoService {
    /*
     * 自定义扩展
     */

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public List<OrderInfo> getOrderInfoList() {
        // 查询所有评价信息
        return orderInfoMapper.selectAll();
    }

    public OrderInfo queryOneOrderInfo(Integer id) {
        // 查询单条竞拍者评价
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    public Integer insertOrderInfo(OrderInfo orderInfo) {
        // 添加一条竞拍者评价
        return orderInfoMapper.insert(orderInfo);
    }

    public Integer updateOneOrderInfo(OrderInfo orderInfo) {
        // 修改一条竞拍者评价
        return orderInfoMapper.updateByPrimaryKey(orderInfo);
    }

    public Integer deleteOneOrderInfo(Integer id) {
        // 删除一条竞拍者评价
        return orderInfoMapper.deleteByPrimaryKey(id);
    }

    public Integer getOrderInfoCount() {
        // 查收订单数量
        return orderInfoMapper.getOrderInfoCount();
    }

    public List<OrderInfo> queryOrderInfoByAuctionRecordId(Integer id) {
        // 根据记录ID查询订单
        return orderInfoMapper.queryOrderInfoByAuctionRecordId(id);
    }

}
