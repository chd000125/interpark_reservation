package edu.du.reservation.controller;

import edu.du.reservation.service.TokenResolverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final TokenResolverService tokenResolverService;

    @GetMapping("/check-user")
    public ResponseEntity<?> checkUser(@RequestParam String userId) {
        Optional<Map<String, Object>> claims = tokenResolverService.resolveUserClaims(userId);

        if (claims.isPresent()) {
            return ResponseEntity.ok(claims.get());
        } else {
            return ResponseEntity.status(401).body("Invalid or missing token");
        }
    }
}
