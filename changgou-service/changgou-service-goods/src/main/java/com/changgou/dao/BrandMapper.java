package com.changgou.dao;

import pojo.Brand;
import tk.mybatis.mapper.common.Mapper;

/**
 * DAO使用通用Mapper,DAP接口需要继承Mapper接口
 * 增加数据：直接调用Mapper.insert()方法 / insertSelective()
 *
 * 修改数据：调用Mapper.update(T) / updateByPrimaryKey()
 *
 * 查询数据：根据ID查询： Mapper.selectByPrimaryKey(ID)
 *         条件查询：Mapper.select()
 */
public interface BrandMapper extends Mapper<Brand> {

}
