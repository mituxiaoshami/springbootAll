package com.example.springbootAll.util;

import com.example.springbootAll.entity.SortForm;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @Author: sea
 * @Description: 分页工具类
 * @Date: 11:18 2017/8/14
 */
public class PageUtils {

    /**
     * 获取基础分页对象
     * @param page 页数
     * @param size 条数
     * @param sortForm 排序对象
     * @return
     */
    public static Pageable basicPage(Integer page, Integer size, SortForm... sortForm) {
        Sort sort = SortUtils.basicSort(sortForm);
        page = (page == null || page<0)?0:page;
        size = (size == null || size<=0)?10:size;
        Pageable pageable = new PageRequest(page,size,sort);
        return pageable;
    }

    /**
     * 获取基础分页对象，每页默认10条
     * @param page 页码
     * @param sortForm 排序对象
     * @return
     */
    public static Pageable basicPage(Integer page,SortForm... sortForm) {
        return basicPage(page,0,sortForm);
    }

    /**
     * 获取基础分页对象，排序方式默认降序
     * @param page 页码
     * @param size 条数
     * @param orderField 排序字段
     * @return
     */
    public static Pageable basicPage(Integer page,Integer size,String orderField) {
        return basicPage(page,size,new SortForm("DESC",orderField));
    }

    /**
     * 获取基础分页对象，每页默认条数10条，排序方式默认降序
     * @param page 页码
     * @param orderField 排序字段
     * @return
     */
    public static Pageable basicPage(Integer page,String orderField) {
        return basicPage(page,0,new SortForm("DESC",orderField));
    }


}
