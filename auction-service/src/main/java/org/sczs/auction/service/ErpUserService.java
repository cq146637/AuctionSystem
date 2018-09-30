package org.sczs.auction.service;

import org.sczs.auction.dao.ErpUserMapper;
import org.sczs.auction.domain.ErpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ErpUserService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class ErpUserService {
    /*
     * 自定义扩展
     */

    @Autowired
    private ErpUserMapper erpUserMapper;

    // 查询所有的erp用户信息
    public List<ErpUser> selectAllErpUser() {
        return erpUserMapper.selectAll();
    }

    public ErpUser getLoginResult(ErpUser erpUser) {
        return erpUserMapper.selectByNameAndPass(erpUser);
    }
}
