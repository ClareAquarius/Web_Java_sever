package com.example.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
//这个配置类设置了一个带有自定义JSON序列化器和特定设置的对象映射器的RedisTemplate bean，用于在Redis中进行键值的序列化和反序列化
@Configuration
//@Configuration注解标记此类为配置类，提供了bean定义
public class MyRedisConfig {
    @Resource
//    @Resource注解用于注入RedisConnectionFactory bean。
    private RedisConnectionFactory factory;
    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
//        将RedisConnectionFactory设置为RedisTemplate的连接工厂
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        将键序列化器设置为StringRedisSerializer，用于将键序列化为字符串。

//        创建一个Jackson2JsonRedisSerializer，用于将值序列化为JSON，使用Jackson库进行序列化。
        Jackson2JsonRedisSerializer serializer=new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(serializer);

//        创建并配置一个ObjectMapper，以自定义序列化和反序列化设置。
        ObjectMapper om=new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        om.setTimeZone(TimeZone.getDefault());
        om.configure(MapperFeature.USE_ANNOTATIONS,false);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);


        serializer.setObjectMapper(om);
        return redisTemplate;
    }
}
