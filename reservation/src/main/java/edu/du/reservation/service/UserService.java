package edu.du.reservation.service;


import edu.du.reservation.repository.UserRepository;
import edu.du.reservation.util.JwtUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final StringRedisTemplate redisTemplate;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UserService(StringRedisTemplate redisTemplate, JwtUtil jwtUtil, UserRepository userRepository) {
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    // Redis에서 토큰 가져오기
    public Optional<String> getTokenFromRedis(String userId) {
        String token = redisTemplate.opsForValue().get("JWT_TOKEN:" + userId);
        return Optional.ofNullable(token);
    }

    // 토큰 검증 후 DB에 저장
    @Transactional
    public Optional<UserEntity> validateAndSaveUser(String userId) {
        return getTokenFromRedis(userId)
                .filter(jwtUtil::validateToken)
                .map(jwtUtil::extractClaims)
                .map(this::saveUser);
    }

    // JSON 데이터를 UserEntity로 변환 후 저장
    private UserEntity saveUser(Map<String, Object> claims) {
        UserEntity user = new UserEntity();
        user.setUsername((String) claims.get("username"));
        user.setEmail((String) claims.get("email"));
        user.setRole((String) claims.get("role"));
        return userRepository.save(user);
    }
}
