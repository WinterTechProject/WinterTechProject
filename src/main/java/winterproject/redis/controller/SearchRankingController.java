package winterproject.redis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import winterproject.redis.dto.RankingResponse;
import winterproject.redis.dto.SearchRequest;
import winterproject.redis.service.SearchRankingService;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
@Tag(name = "검색어 랭킹 API", description = "실시간 검색어 랭킹 관리 API")
public class SearchRankingController {
    private final SearchRankingService searchRankingService;

    @Operation(summary = "검색어 등록", description = "새로운 검색어를 등록합니다.")
    @ApiResponse(responseCode = "200", description = "검색어 등록 성공")
    @PostMapping("/search")
    public ResponseEntity<Void> addSearch(
            @RequestBody @Valid SearchRequest request
    ) {
        searchRankingService.addSearchKeyword(
                request.getKeyword(),
                request.getGender(),
                request.getRegion(),
                request.getAgeGroup()
        );
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "전체 검색어 순위 조회", description = "전체 검색어의 실시간 순위를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/total")
    public ResponseEntity<List<RankingResponse>> getTotalRanking() {
        return ResponseEntity.ok(searchRankingService.getTotalRanking());
    }

    @Operation(summary = "성별 검색어 순위 조회", description = "성별 기준 검색어 순위를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<RankingResponse>> getRankingByGender(
            @Parameter(description = "성별 (M: 남성, F: 여성)", example = "F")
            @PathVariable String gender
    ) {
        return ResponseEntity.ok(searchRankingService.getRankingByGender(gender));
    }

    @Operation(summary = "지역별 검색어 순위 조회", description = "지역별 검색어 순위를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/region/{region}")
    public ResponseEntity<List<RankingResponse>> getRankingByRegion(
            @Parameter(description = "지역명 (예: SEOUL, BUSAN)", example = "SEOUL")
            @PathVariable String region
    ) {
        return ResponseEntity.ok(searchRankingService.getRankingByRegion(region));
    }

    @Operation(summary = "연령대별 검색어 순위 조회", description = "연령대별 검색어 순위를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/age/{ageGroup}")
    public ResponseEntity<List<RankingResponse>> getRankingByAge(
            @Parameter(description = "연령대 (10, 20, 30, 40)", example = "20")
            @PathVariable int ageGroup
    ) {
        return ResponseEntity.ok(searchRankingService.getRankingByAge(ageGroup));
    }
}
