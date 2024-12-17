package winter.com.redis.service;

public interface RedisService {
    Object searchKeyWord(String q);

    Object rankingKeyWord();
}
