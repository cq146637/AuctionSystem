package org.sczs.auction.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * BaseDao 集成通用 Mapper
 * 特别注意，该接口不能被mybatis扫描到，否则会出错
 *
 * @author LongShu 2018/02/01
 * @see tk.mybatis.mapper.common.BaseMapper
 * @see Mapper
 */
public interface BaseDao<T> extends
        Mapper<T>,
        MySqlMapper<T> {

}
