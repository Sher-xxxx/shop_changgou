package com.changgou.controller;

import com.changgou.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Brand;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin //跨域
/**
 * 跨域：A域名访问B域名的数据
 * 域名或者请求端口或协议不一致的时候就跨域了
 * 加上@CrossOrigin：允许跨域
 */
public class BrandController {

    @Autowired
    private BrandService brandService;
    /**
     * 查询所有品牌
     */
    @GetMapping
    public Result<List<Brand>> findAll(){

        // 查询所有品牌
        List<Brand> brands = brandService.findAll();
        // 响应结果封装
        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌成功", brands);
    }

    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id")Integer id){
        Brand brand = brandService.findByID(id);
        return new Result<Brand>(true, StatusCode.OK, "ID查询成功 ",brand);
    }

    /**
     * 增加品牌
     */
    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id")Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id")Integer id){
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand){
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "条件查询成功", list);
    }

    //分页查询
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page")Integer page,
                                    @PathVariable(value = "size")Integer size){
        PageInfo<Brand> pageInfo = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页查询成功", pageInfo);
    }

    // 条件分页搜索
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand,
                                            @PathVariable(value = "page")Integer page,
                                            @PathVariable(value = "size")Integer size){
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页查询成功", pageInfo);
    }

}
