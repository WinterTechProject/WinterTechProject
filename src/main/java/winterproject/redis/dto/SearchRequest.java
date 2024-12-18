package winterproject.redis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "검색어 등록 요청")
public class SearchRequest {
    @Schema(description = "검색 키워드", example = "아이유")
    private String keyword;

    @Schema(description = "성별 (M: 남성, F: 여성)", example = "F")
    private String gender;

    @Schema(description = "지역", example = "SEOUL")
    private String region;

    @Schema(description = "연령대", example = "20")
    private int ageGroup;
}
