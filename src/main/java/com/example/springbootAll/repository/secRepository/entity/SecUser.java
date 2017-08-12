package com.example.springbootAll.repository.secRepository.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:28 2017/8/8
 */
@Entity
@Data
@Table(name = "user")
public class SecUser implements Serializable {

    // 存储到Redis 要实现对象的序列化 不然控制台会报出异常
    // java.lang.IllegalArgumentException:DefaultSerializer requires a Serializable payload but received an Object of type

    //序列化的标识,序列化和反序列化之间就是通过这个标识来验证
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true,length = 10)
    private String userName;

    @Column(nullable = false,length = 10)
    private String passWord;

    @Column(nullable = false, unique = true,length = 20)
    private String email;

    @Column(nullable = true, unique = true,length = 10)
    private String nickName;

    @Column(nullable = false,length = 20)
    private String regTime;

    //不需要和数据库来关联只是在展示的时候做计算，只需要加上@Transient属性既可
    @Transient
    private String  cityName;

    public SecUser() {

    }

    public SecUser(String userName, String passWord, String email, String nickName, String regTime) {
        this();
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.nickName = nickName;
        this.regTime = regTime;
    }

}
