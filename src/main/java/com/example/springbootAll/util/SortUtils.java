package com.example.springbootAll.util;


import com.example.springbootAll.entity.SortForm;
import org.springframework.data.domain.Sort;

/**
 * @Author: sea
 * @Description: 排序工具类
 * @Date: 11:02 2017/8/14
 */
public class SortUtils {

    //如果没有传入排序的字段和排序方式 默认情况下就对列名的主键id斤排序 倒序
    public static Sort basicSort() {
        return basicSort("DESC","id");
    }


    public static Sort basicSort(String orderType,String orderField) {
        Sort sort = new Sort(Sort.Direction.fromString(orderType),orderField);
        return sort;
    }

    public static Sort basicSort(SortForm...form) {
        Sort sort = null;
        for (SortForm sortForm : form) {
            if (sort == null) {
                sort = new Sort(Sort.Direction.fromString(sortForm.getOrderType()),sortForm.getOrderFiled());
            }else {
                sort = sort.and(new Sort(Sort.Direction.fromString(sortForm.getOrderType()),sortForm.getOrderFiled()));
            }
        }
        return sort;
    }
}
