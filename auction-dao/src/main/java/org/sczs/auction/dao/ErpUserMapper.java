package org.sczs.auction.dao;

import org.sczs.auction.domain.ErpUser;

import java.util.List;

public interface ErpUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ErpUser record);

    ErpUser selectByPrimaryKey(Integer id);

    List<ErpUser> selectAll();

    int updateByPrimaryKey(ErpUser record);

    ErpUser selectByNameAndPass(ErpUser erpUser);
}