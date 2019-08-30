package com.chmielewska.networkingapplication.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUserName(String userName);

    Optional<User> findById(Long id);
}
