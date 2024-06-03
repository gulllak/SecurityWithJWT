package ru.evgenii.SecurityWithJWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.evgenii.SecurityWithJWT.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
