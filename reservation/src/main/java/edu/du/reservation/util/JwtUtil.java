package edu.du.reservation.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰에서 사용자명 추출
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료됨: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("잘못된 토큰 형식: " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("서명 검증 실패: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("토큰이 비어있거나 null입니다.");
        }
        return false;
    }

    // Claims 추출 (토큰에서 데이터 가져오기)
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
