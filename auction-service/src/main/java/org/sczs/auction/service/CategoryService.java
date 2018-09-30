package org.sczs.auction.service;

import org.sczs.auction.dao.CategoryMapper;
import org.sczs.auction.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService
 *
 * @author chain-generator 2018-09-04
 */
@Service
public class CategoryService {
    /*
     * 自定义扩展
     */

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getCategoryList() {
        // 查询所有分类信息
        return categoryMapper.selectAll();
    }

    public Category queryOneCategory(Integer id) {
        // 根据ID查询分类
        return categoryMapper.selectByPrimaryKey(id);
    }

    public Integer insertOneCategory(Category category) {
        // 插入分类数据
        return categoryMapper.insert(category);
    }

    public Integer updateOneCategory(Category category) {
        // 更新分类数据
        return categoryMapper.updateByPrimaryKey(category);
    }

    public Integer deleteOneCategory(Integer id) {
        // 删除分类数据
        return categoryMapper.deleteByPrimaryKey(id);
    }

}
