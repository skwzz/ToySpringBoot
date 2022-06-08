package com.skwzz.toyspringboot.redis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisBasicTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    void 등록후_조회(){
        String key = "A";
        String data = "Amazon";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(key, data);

        String value = valueOperations.get(key);
        assertThat(value).isEqualTo(data);
    }

    @Test
    void 등록후_수정(){
        String key = "B";
        String beforeData = "Bee";
        String afterData = "Best";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(key, beforeData);
        valueOperations.set(key, afterData);

        String value = valueOperations.get(key);
        assertThat(value).isEqualTo(afterData);
    }

    @Test
    void 만료_조회() throws InterruptedException {
        String key = "C";
        String data = "Car";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);

        redisTemplate.expire(key, 1, TimeUnit.SECONDS);
        Thread.sleep(1500);

        String value = valueOperations.get(key);
        assertThat(value).isNull();
    }

    @Test
    void 모든데이터_조회(){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Set<String> redisKeys = redisTemplate.keys("*");
        List<String> keysList = new ArrayList<>();
        Iterator<String> it = redisKeys.iterator();
        while (it.hasNext()) {
            String data = it.next();
            keysList.add(data);
        }
        for(String s:keysList){
            System.out.println(s);
        }
    }

    @Test
    void 모든데이터_삭제(){
        redisTemplate.delete(redisTemplate.keys("*"));
        Set<String> redisKeys = redisTemplate.keys("*");
        assertThat(redisKeys).isEmpty();
    }
}
