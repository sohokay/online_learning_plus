package top.musuixin.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.musuixin.redis.service.RedisService;

import java.util.concurrent.TimeUnit;

/**
 * @author musuixin
 * @date 2020-03-26 10:09
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    @Qualifier("myRedis")
    private RedisTemplate redisTemplate;
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, int seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
