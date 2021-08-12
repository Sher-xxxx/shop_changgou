package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Brand;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    /**
     * 查询所有
     * @return brandMapper.selectAll()
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return brandMapper.selectByPrimaryKey(Object)
     */
    @Override
    public Brand findByID(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        // 方法中带有Selective，会忽略空值
        brandMapper.insertSelective(brand);
    }

    /**
     * 根据ID修改品牌
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 多条件搜索
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page: 当前页
     * @param size: 每页显示多少条
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        // 查询集合
        List<Brand> brands = brandMapper.selectAll();
        return new PageInfo<Brand>(brands);
    }

    /**
     * 分页条件搜索
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        // 分页
        PageHelper.startPage(page, size);
        // 搜索数据
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);

        return new PageInfo<Brand>(brands);
    }

    //条件构建
    public Example createExample(Brand brand){
        // 自定义条件搜索对象Example
        Example example = new Example(Brand.class);
        Example.Criteria criteria= example.createCriteria(); // 条件构造器

        if(brand != null){
            if(!StringUtils.isEmpty(brand.getName())){
                /**
                 * 1. brand属性名
                 * 2. 占位符参数，搜索条件
                 */
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }
}
