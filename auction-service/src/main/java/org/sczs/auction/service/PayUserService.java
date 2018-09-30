package org.sczs.auction.service;

import org.sczs.auction.dao.PayUserMapper;
import org.sczs.auction.domain.PayUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PayUserService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class PayUserService {
    /*
     * 自定义扩展
     */

    @Autowired
    PayUserMapper payUserMapper;

    public int deleteByPrimaryKey(Integer id){return payUserMapper.deleteByPrimaryKey(id);}

    public int insert(PayUser record){return payUserMapper.insert(record);}

    public int updateByPrimaryKey(PayUser record){return payUserMapper.updateByPrimaryKey(record);}

    // 查询所有的竞拍者信息
    public List<PayUser> getPayUserList(){
        return payUserMapper.selectAll();
    }

    //根据name查询password
    public PayUser getPayUserByCondition(PayUser payUser){
        return payUserMapper.getPayUserByCondition(payUser);
    }

    public PayUser queryOnePayUser(Integer id) {
        // 查询单条竞拍者信息
        return payUserMapper.selectByPrimaryKey(id);
    }

    public Integer getPayUserCount() {
        // 查询拍卖者数量
        return payUserMapper.getPayUserCount();
    }

}
