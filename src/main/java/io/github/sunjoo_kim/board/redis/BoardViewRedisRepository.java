package io.github.sunjoo_kim.board.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class BoardViewRedisRepository {

    private final RedisTemplate<String, Long> redisTemplate;

    public void incrementViewCount(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public Long getViewCount(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setViewCount(String key, Long value) {
        redisTemplate.opsForValue().set(key, value);
    }
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}