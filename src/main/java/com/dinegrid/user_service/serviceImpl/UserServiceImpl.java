package com.dinegrid.user_service.serviceImpl;

import com.dinegrid.user_service.dto.AuthResponse;
import com.dinegrid.user_service.dto.LoginRequest;
import com.dinegrid.user_service.dto.RegisterRequest;
import com.dinegrid.user_service.dto.UserDto;
import com.dinegrid.user_service.entity.UserEntity;
import com.dinegrid.user_service.mapper.UserMapper;
import com.dinegrid.user_service.repository.UserRepository;
import com.dinegrid.user_service.security.JwtUtil;
import com.dinegrid.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Override
    public UserDto register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail()))
            throw new RuntimeException("Email already registered");

        UserEntity userEntity = userMapper.toUserEntity(registerRequest);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        UserEntity savedUser = userRepository.save(userEntity);
        log.info("Registering new user: {}", savedUser.getEmail());

        return userMapper.toUserDto(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();

        String token = jwtUtil.generateToken(userDetails);
        log.info("Generated JWT for user: {}", user.getEmail());

        return new AuthResponse(token);
    }
}
