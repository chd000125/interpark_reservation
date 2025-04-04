package edu.du.reservation.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);

}