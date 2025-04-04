package edu.du.reservation.controller;

import edu.du.reservation.service.UserService;
import edu.du.reservation.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // React에서 요청을 보내면 실행됨
    @GetMapping("/sync")
    public ResponseEntity<?> syncUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid Authorization header");
        }

        String userId = "user123";  // 예시로 Redis에서 찾을 userId (프론트에서 전달 가능)
        Optional<UserEntity> savedUser = userService.validateAndSaveUser(userId);

        return savedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body("Invalid token or user not found"));
    }
}
