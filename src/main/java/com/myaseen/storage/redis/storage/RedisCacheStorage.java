package com.myaseen.storage.redis.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RedisCacheStorage {

    private Logger LOGGER = Logger.getLogger(RedisCacheStorage.class.getName());
    private RedisTemplate<String,Object> template;

    @Autowired
    public RedisCacheStorage(RedisTemplate<String,Object> redisTemplate){
        template = redisTemplate;
    }

    public void put(Object key,Object value){
        template.opsForSet().add(getKey(key),value);
    }

    public void putAll(Object key, Map<Object,Object> value){
        long time = System.currentTimeMillis();
        template.opsForHash().putAll(getKey(key),value);
        LOGGER.log(Level.INFO,"time to store hash with "+value.keySet().size()+" keys in "+(time-System.currentTimeMillis())+"ms");
    }

    public Map<Object,Object> getAll(Object key){
        long time = System.currentTimeMillis();
        Map<Object,Object> value =template.opsForHash().entries(getKey(key));
        LOGGER.log(Level.INFO,"time to store hash with "+value.keySet().size()+" keys in "+(time-System.currentTimeMillis())+"ms");
        return value;
    }

    protected String getKey(Object key){
        return key.toString();
    }

}
