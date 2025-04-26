package com.dinegrid.user_service.controller;

import com.dinegrid.user_service.dto.AuthResponse;
import com.dinegrid.user_service.dto.LoginRequest;
import com.dinegrid.user_service.dto.RegisterRequest;
import com.dinegrid.user_service.dto.UserDto;
import com.dinegrid.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest registerRequest) {
        log.info("Registering user with email: {}", registerRequest.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/user/profile")
    public ResponseEntity<String> getProfile(Authentication authentication) {
        String email = authentication.getName();
        String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();

        return ResponseEntity.ok("Logged in as: " + email + " | Role: " + role);
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> getAdminDashboard(Authentication authentication) {
        log.info("Admin accessed dashboard: {}", authentication.getName());
        return ResponseEntity.ok("Welcome Admin: " + authentication.getName());
    }

}
