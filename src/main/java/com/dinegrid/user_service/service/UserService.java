package com.dinegrid.user_service.service;


import com.dinegrid.user_service.dto.AuthResponse;
import com.dinegrid.user_service.dto.LoginRequest;
import com.dinegrid.user_service.dto.RegisterRequest;
import com.dinegrid.user_service.dto.UserDto;

public interface UserService {

    UserDto register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
