package winter.com.redis.service;

public interface RedisService {
    String searchKeyWord(String q);

    Object rankingKeyWord();
}
