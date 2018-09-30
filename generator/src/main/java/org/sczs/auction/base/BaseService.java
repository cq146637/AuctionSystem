package org.sczs.auction.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * BaseService
 *
 * @author LongShu 2018/02/01
 */
@Named
public abstract class BaseService<E> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    protected BaseDao<E> dao;

    public BaseDao<E> getDao() {
        return dao;
    }
    

    //********************************************//

    public int insertEntry(E entity) {
        return getDao().insert(entity);
    }

    public int insertSelective(E entity) {
        return getDao().insertSelective(entity);
    }

    public int insertList(List<E> recordList) {
        return getDao().insertList(recordList);
    }

    //********************************************//

    /*public int saveOrUpdate(E entity) {
        Long id = entity.getId();

        int result;
        if (id != null && id > 0) {
            result = this.updateByKey(entity);
        } else {
            result = this.insertEntry(entity);
        }
        return result;
    }*/

    public int updateByKey(E entity) {
        return getDao().updateByPrimaryKeySelective(entity);
    }

    //********************************************//

    public E selectEntry(Serializable key) {
        return getDao().selectByPrimaryKey(key);
    }

    public E selectOneEntry(E condition) {
        PageHelper.offsetPage(0, 1, false);
        return getDao().selectOne(condition);
    }

    public List<E> selectEntryList(E condition) {
        return getDao().select(condition);
    }

    //********************************************//

    public Page<E> selectPage(E condition, int pageNum, int pageSize) {
        // 物理分页, 包含count查询
        PageHelper.startPage(pageNum, pageSize);
        // list -> com.github.pagehelper.Page
        Page<E> page = (Page<E>) getDao().select(condition);
        return page;
    }

    public Page<E> selectPage(E condition, Page<E> page) {
        // 物理分页, 包含count查询
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        // list -> com.github.pagehelper.Page
        page = (Page<E>) getDao().select(condition);
        return page;
    }

    public int selectCount(E condition) {
        return getDao().selectCount(condition);
    }

    //********************************************//

    public int updateByCondition(E entity, Example condition) {
        return getDao().updateByExampleSelective(entity, condition);
    }

    public E selectOneByCondition(Example condition) {
        PageHelper.offsetPage(0, 1, false);
        return getDao().selectOneByExample(condition);
    }

    public List<E> selectByCondition(Example condition) {
        return getDao().selectByExample(condition);
    }

    public Page<E> selectPageByCondition(Example condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<E> page = (Page<E>) getDao().selectByExample(condition);
        return page;
    }

    public Page<E> selectPageByCondition(Example condition, Page<E> page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.isCount());
        page = (Page<E>) getDao().selectByExample(condition);

        return page;
    }

    public int selectCountByCondition(Example condition) {
        return getDao().selectCountByExample(condition);
    }

    public List<E> queryByCompanyId(Long companyId,Class<E> eClass) {
        Example example = new Example(eClass);
        example.createCriteria().andEqualTo("yn", Boolean.TRUE);
        example.and().orEqualTo("companyId", companyId);

        List<E> info = selectByCondition(example);
        return info;
    }
}
