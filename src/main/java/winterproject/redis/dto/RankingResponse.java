package winterproject.redis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "검색어 순위 응답")
public class RankingResponse {
    @Schema(description = "순위", example = "1")
    private int rank;

    @Schema(description = "검색 키워드", example = "아이유")
    private String keyword;

    @Schema(description = "검색 점수", example = "3.0")
    private Double score;
}
