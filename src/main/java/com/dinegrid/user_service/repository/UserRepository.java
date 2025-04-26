package com.dinegrid.user_service.repository;

import com.dinegrid.user_service.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(@Email(message = "Invaid email format") @NotBlank(message = "Email is required") String email);

    Optional<UserEntity> findByEmail(String email);
}
