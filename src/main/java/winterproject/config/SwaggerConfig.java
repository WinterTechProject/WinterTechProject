package winterproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("실시간 검색어 랭킹 API")
                        .version("v1.0")
                        .description("Redis를 이용한 실시간 검색어 랭킹 API 문서"));
    }
}