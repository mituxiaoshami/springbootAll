package com.example.springbootAll.entity;


import lombok.Data;

/**
 * @Author: sea
 * @Description: 排序对象
 * @Date: 10:52 2017/8/14
 */
@Data
public class SortForm {

    //排序方式
    private String orderType;

    //排序字段
    private String orderFiled;

    public SortForm(String orderType, String orderFiled) {
        this.orderType = orderType;
        this.orderFiled = orderFiled;
    }

    //默认为DESC排序
    public SortForm(String orderFiled) {
        this.orderType = "DESC";
        this.orderFiled = orderFiled;
    }
}
