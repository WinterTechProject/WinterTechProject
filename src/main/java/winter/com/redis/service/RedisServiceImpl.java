package winter.com.redis.service;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import winter.com.vo.KeyWord;

import java.util.Set;

@Service
@RedisHash
public class RedisServiceImpl implements RedisService {

    private static final String SEARCH_KEYWORD_RANK = "search:keyword:rank";
    private static final String SEARCH_KEYWORD_META = "search:keyword:meta";
    private final RedisTemplate<String, String> redisTemplate;

    RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String searchKeyWord(String q) {
        redisTemplate.opsForHash().putIfAbsent(SEARCH_KEYWORD_META, q, KeyWord.of(q));
        redisTemplate.opsForZSet().addIfAbsent(SEARCH_KEYWORD_RANK, q, 0);
        redisTemplate.opsForZSet().incrementScore(SEARCH_KEYWORD_RANK, q, 1);
        return q;
    }

    @Override
    public Object rankingKeyWord() {
        Set<ZSetOperations.TypedTuple<String>> topKeywords = redisTemplate.opsForZSet().reverseRangeWithScores(SEARCH_KEYWORD_RANK, 0, 10);
        return topKeywords;
    }
}
