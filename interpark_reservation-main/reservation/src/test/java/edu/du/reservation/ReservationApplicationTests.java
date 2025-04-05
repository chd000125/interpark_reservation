package edu.du.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationApplicationTests {

	@Test
	void contextLoads() {
	}

	@SpringBootTest
	@AutoConfigureMockMvc
	public class ReservationControllerTest {

		@Autowired
		private MockMvc mockMvc;

		@Autowired
		private StringRedisTemplate redisTemplate;

		@Autowired
		private JwtUtil jwtUtil;

		@Test
		void testTokenValidation() throws Exception {
			// given
			String token = createTestToken(); // 테스트용 JWT 발급
			redisTemplate.opsForValue().set("JWT_TOKEN:test-user", token);

			// when & then
			mockMvc.perform(get("/api/reservations/check-user")
							.param("userId", "test-user"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.userId").value("test-user"));
		}

		private String createTestToken() {
			Map<String, Object> claims = Map.of(
					"userId", "test-user",
					"email", "test@example.com"
			);

			return Jwts.builder()
					.setClaims(claims)
					.signWith(jwtUtil.getKey(), SignatureAlgorithm.HS256)
					.compact();
		}
	}

}
