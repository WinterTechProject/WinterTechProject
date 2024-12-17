package winter.com.redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import winter.com.redis.service.RedisService;

@RestController
@RequestMapping("redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @GetMapping("/search")
    public ResponseEntity<?> searchKeyWord(@RequestParam String q) {
        redisService.searchKeyWord(q);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ranking")
    public ResponseEntity<?> ranking() {
        return ResponseEntity.ok().body(redisService.rankingKeyWord());
    }
}
