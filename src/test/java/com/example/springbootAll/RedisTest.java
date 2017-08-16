package com.example.springbootAll;

import com.example.springbootAll.entity.priEntity.PriUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Author: sea
 * @Description:
 * @Date: 14:27 2017/8/8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 首先这里注入了RedisTemplate对象，联想到Spring 的 JdbcTemplate
     * RedisTemplate封装了RedisConnection，具有连接管理，序列化和Redis操作等功能
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        PriUser user=new PriUser("aa@126.com", "aa", "aa123456", "aa","123");
        // Redis操作视图接口类用的是ValueOperations 还有其他的操作视图ListOperations、SetOperations、ZSetOperations 和 HashOperations
        ValueOperations<String, PriUser> operations=redisTemplate.opsForValue();
        operations.set("hello", user);
        operations.set("hello world", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }


}
