package com.dinegrid.user_service.mapper;

import com.dinegrid.user_service.dto.RegisterRequest;
import com.dinegrid.user_service.dto.UserDto;
import com.dinegrid.user_service.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(RegisterRequest registerRequest);

    UserDto toUserDto(UserEntity userEntity);
}
