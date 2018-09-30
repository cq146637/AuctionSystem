package org.sczs.auction.service;

import org.sczs.auction.dao.RolePermissionMapper;
import org.sczs.auction.domain.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RolePermissionService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class RolePermissionService {
    /*
     * 自定义扩展
     */

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    public List<RolePermission> selectAll(){
        return rolePermissionMapper.selectAll();
    }

}
