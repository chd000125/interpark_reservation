package edu.du.reservation.service

import com.ticketing.reservation.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenResolverService {

    private final StringRedisTemplate redisTemplate;
    private final JwtUtil jwtUtil;

    // Redis에서 토큰 가져오기
    public Optional<String> getTokenFromRedis(String userId) {
        String redisKey = "JWT_TOKEN:" + userId;
        String token = redisTemplate.opsForValue().get(redisKey);
        return Optional.ofNullable(token);
    }

    // Redis에서 토큰 가져와 검증하고 클레임 추출
    public Optional<Map<String, Object>> resolveUserClaims(String userId) {
        return getTokenFromRedis(userId)
                .filter(jwtUtil::validateToken)
                .map(jwtUtil::extractClaims);
    }
}
