package io.github.sunjoo_kim.board.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BoardViewRedisRepository {

    private final RedisTemplate<String, Long> redisTemplate;

    public Long getStayTime(String key,Long member) {
       //  zset 에서 key에 해당하는 값을 Redis에서 가져옵니다.
        log.info("key: {}, member: {}", key, member);
        Double score = redisTemplate.opsForZSet().score(key, member);
        return (score != null) ? score.longValue() : 0L;
    }

}