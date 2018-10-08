package com.myaseen.storage.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Test
    public void contextLoads() {

        System.out.println("CommandLineRunner running in the UnsplashApplication class...");
        for(long i=0;i<10000;i++) {
            long j = i;
        }
    }

}
