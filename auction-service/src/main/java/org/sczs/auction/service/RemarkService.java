package org.sczs.auction.service;

import org.sczs.auction.dao.RemarkMapper;
import org.sczs.auction.domain.Remark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RemarkService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class RemarkService {
    /*
     * 自定义扩展
     */

    @Autowired
    private RemarkMapper remarkMapper;

    public List<Remark> getRemarkList() {
        // 查询所有竞拍者评价
        return remarkMapper.selectAll();
    }

    public Remark queryOneRemark(Integer id) {
        // 查询单条竞拍者评价
        return remarkMapper.selectByPrimaryKey(id);
    }

    public Integer insertOneRemark(Remark remark) {
        // 添加一条竞拍者评价
        return remarkMapper.insert(remark);
    }

    public Integer updateOneRemark(Remark remark) {
        // 修改一条竞拍者评价
        return remarkMapper.updateByPrimaryKey(remark);
    }

    public Integer deleteOneRemark(Integer id) {
        // 删除一条竞拍者评价
        return remarkMapper.deleteByPrimaryKey(id);
    }

    /**
    *@Description: 根据productId查询remark
    *@create: 2018/9/13
    *@Author: SLJ
    *@Param:
    *@return:
    */
    public List<Remark> getRemarkAllByProductId(Integer productId){
        return remarkMapper.getRemarkAllByProductId(productId);
    }

}
