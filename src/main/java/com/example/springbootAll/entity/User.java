package com.example.springbootAll.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: sea
 * @Description:
 * @Date: 13:28 2017/8/8
 */
@Entity
@Data
public class User implements Serializable {

    /**
     * 为什么对象类要实现序列化:
     *
     * 序列化的过程就是对象写入字节流和从字节流中读取对象。
     * 将对象状态转换成字节流之后，可以用java.io包中的各种字节流类将其保存到文件中，
     * 管道到另一线程中或通过网络连接将对象数据发送到另一主机。
     *
     * 采用数据流来存储 ,以数据流传输会提高网络传输速度，在数据流进行操作可以直接操作数据流，少了对数据表进行操作的过程，减少数据出错的几率.
     *
     * 应用场景:
     * 对象序列化可以实现分布式对象。
     * 例:现在做一个项目，项目是分工合作的，并且你和其他小组成员不在同一个城市，
     * 那么你要如何把你写的那些类给其他小组成员呢？这个时候就要用到序列化了，
     * 简单的说：序列化就是将内存中的类或者对象（你写的类都是存储在内存中的）变成可以存储到存储媒介中的流，
     * 你将类序列化成流之后可以通过互联网传输给别人，你也可以反序列化将别人的序列化流转换成内存中的对象。
     */

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

    public User() {

    }

    public User(String userName,String passWord,String email,String nickName,String regTime) {
        this();
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.nickName = nickName;
        this.regTime = regTime;
    }

}
