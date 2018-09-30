package org.sczs.auction.dao;

import org.sczs.auction.domain.Remark;

import java.util.List;

public interface RemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Remark record);

    Remark selectByPrimaryKey(Integer id);

    List<Remark> selectAll();

    int updateByPrimaryKey(Remark record);

    //根据productId查询remark
    List<Remark> getRemarkAllByProductId(Integer productId);
}