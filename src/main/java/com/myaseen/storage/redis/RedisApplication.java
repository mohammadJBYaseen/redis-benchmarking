package com.myaseen.storage.redis;

import com.myaseen.storage.redis.storage.RedisCacheStorage;
import com.myaseen.storage.redis.storage.data.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class RedisApplication {

    public static Logger LOGGER = Logger.getLogger(RedisApplication.class.getName());
    @Autowired
    RedisCacheStorage storage;
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,100,4, TimeUnit.HOURS, new ArrayBlockingQueue<>(100));
            int x= 3;
            System.out.println("CommandLineRunner running in the UnsplashApplication class...");
//            LOGGER.log(Level.INFO,"generating 10000 objects and store them in redis");
//            for(long i=0;i<10000;i++) {
//                TestObject testObject = new TestObject();
//                LOGGER.log(Level.INFO,"create test object "+i+1);
//                for(int j=0;j<x;j++){
//                    testObject.addField("field:"+j, Arrays.asList(new String[]{"xyyyyyyyyyyyyyyyyyyyyyyasdadadasdada"}));
//                }
//                storage.putAll("testObject:"+i,testObject.getFields());
//            }
//            LOGGER.log(Level.INFO,"sleep for 1 second");
            Thread.sleep(1000);
            LOGGER.log(Level.INFO,"reading 10000 objects and store them in redis");
            for(long i=0;i<10000;i++) {
                LOGGER.log(Level.INFO,"read test object "+i+1);
                storage.getAll("testObject:"+i);
            }

        };
    }
}
