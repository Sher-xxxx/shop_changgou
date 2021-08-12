package com.changgou.service;

import com.github.pagehelper.PageInfo;
import pojo.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有品牌
     */
    List<Brand> findAll();

    /**
     * 根据ID查询
     */
    Brand findByID(Integer id);

    /**
     * 增加品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 根据ID修改品牌数据
     */
    void update(Brand brand);

    /**
     * 根据ID删除品牌数据
     */
    void delete(Integer id);

    /**
     * 根据品牌信息多条件搜索
     */
    List<Brand> findList(Brand brand);

    /**
     * 分页搜索
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /**
     * 分页+条件搜索
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);
}
