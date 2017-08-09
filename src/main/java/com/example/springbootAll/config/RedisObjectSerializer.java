package com.example.springbootAll.config;


import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;


/**
 * @Author: sea
 * @Description: 自定义Redis序列化的实现 用JsonObject的方式实现 因为项目中用的是jackson2JsonRedisSerializer 如果需要用自己实现的 那么就在 RedisConfig
 *                 配置类中template.setValueSerializer()；方法中设置自己实现的序列化方式(RedisObjectSerializer)
 * @Date: 11:08 2017/8/9
 */
public class RedisObjectSerializer implements RedisSerializer<Object>{

    Logger logger = LoggerFactory.getLogger(RedisObjectSerializer.class);

    static final byte[] EMPTY_ARRAY = new byte[0];
    private final Charset charset;

    public RedisObjectSerializer() {
        // TODO Auto-generated constructor stub
        this(Charset.forName("UTF8"));
    }

    public RedisObjectSerializer(Charset charset) {
        // TODO Auto-generated constructor stub
        logger.info("charset:"+charset);
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object object){  //序列化方法
        // TODO Auto-generated method stub
        try {
            JSONObject jsonObject = JSONObject.fromObject(object);
            String jsonString = jsonObject.toString();
            return (jsonString == null ? EMPTY_ARRAY : jsonString.getBytes(charset));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException { //反序列化
        // TODO Auto-generated method stub
        String objectStr = null;
        Object object = null;
        if (bytes == null) {
            return object;
        }
        try {
            objectStr = new String(bytes,charset); //byte数组转换为String
            JSONObject jsonObject = JSONObject.fromObject(objectStr); //String转化为JSONObject
            object = jsonObject;  //返回的是JSONObject类型  取数据时候需要再次转换一下
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return object;
    }
}
