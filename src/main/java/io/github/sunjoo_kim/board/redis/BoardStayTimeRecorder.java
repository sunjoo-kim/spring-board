package io.github.sunjoo_kim.board.redis;

public class PostStayTimeRecorder {
    private static final String POST_STAY_TIME_KEY = "post_stay_time";

    private final RedisTemplate<String, Long> redisTemplate;

    public PostStayTimeRecorder(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void recordStayTime(String postId, long stayTime) {
        String key = POST_STAY_TIME_KEY + ":" + postId;
        redisTemplate.opsForValue().increment(key, stayTime);
    }

    public long getStayTime(String postId) {
        String key = POST_STAY_TIME_KEY + ":" + postId;
        return redisTemplate.opsForValue().get(key);
    }
}
