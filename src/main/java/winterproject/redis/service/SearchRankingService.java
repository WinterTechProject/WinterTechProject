package winterproject.redis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import winterproject.redis.dto.RankingResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchRankingService {
    private static final int RANKING_SIZE = 10; // 랭킹 사이즈를 상수로 정의
    private final RedisTemplate<String, String> redisTemplate;

    // 검색어 저장
    public void addSearchKeyword(String keyword, String gender, String region, int ageGroup) {
        try {
            log.info("검색어 저장 시도 - keyword: {}, gender: {}, region: {}, ageGroup: {}",
                    keyword, gender, region, ageGroup);

            // Redis 연결 확인
            Boolean hasKey = redisTemplate.hasKey("test");
            log.info("Redis 연결 상태: {}", hasKey != null ? "성공" : "실패");

            // 전체 검색어 순위
            Double score = redisTemplate.opsForZSet().incrementScore("total_ranking", keyword, 1);
            log.info("전체 순위 업데이트 결과: {}", score);

            if (score != null) {
                trimRanking("total_ranking");

                // 성별 기준 검색어 순위
                redisTemplate.opsForZSet().incrementScore("gender:" + gender, keyword, 1);
                trimRanking("gender:" + gender);

                // 지역별 검색어 순위
                redisTemplate.opsForZSet().incrementScore("region:" + region, keyword, 1);
                trimRanking("region:" + region);

                // 연령대별 검색어 순위
                redisTemplate.opsForZSet().incrementScore("age:" + ageGroup, keyword, 1);
                trimRanking("age:" + ageGroup);

                log.info("검색어 저장 성공");
            } else {
                throw new RuntimeException("Redis 업데이트 실패");
            }

        } catch (Exception e) {
            log.error("검색어 저장 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("검색어 저장에 실패했습니다: " + e.getMessage());
        }
    }

    // 랭킹을 10개로 유지하는 메서드
    private void trimRanking(String key) {
        redisTemplate.opsForZSet().removeRange(key, 0, -(RANKING_SIZE + 1));
    }

    // 전체 검색어 순위 조회
    public List<RankingResponse> getTotalRanking() {
        return getRankingWithScores("total_ranking");
    }

    // 성별 기준 검색어 순위 조회
    public List<RankingResponse> getRankingByGender(String gender) {
        return getRankingWithScores("gender:" + gender);
    }

    // 지역별 검색어 순위 조회
    public List<RankingResponse> getRankingByRegion(String region) {
        return getRankingWithScores("region:" + region);
    }

    // 연령대별 검색어 순위 조회
    public List<RankingResponse> getRankingByAge(int ageGroup) {
        return getRankingWithScores("age:" + ageGroup);
    }

    // 순위, 검색어, 점수를 포함한 랭킹 조회
    private List<RankingResponse> getRankingWithScores(String key) {
        Set<ZSetOperations.TypedTuple<String>> rankingSet = redisTemplate.opsForZSet()
                .reverseRangeWithScores(key, 0, RANKING_SIZE - 1);

        if (rankingSet == null) {
            return Collections.emptyList();
        }

        List<RankingResponse> result = new ArrayList<>();
        int rank = 1;

        for (ZSetOperations.TypedTuple<String> tuple : rankingSet) {
            result.add(new RankingResponse(
                    rank++,
                    tuple.getValue(),
                    tuple.getScore()
            ));
        }

        return result;
    }
}
