package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;
import com.az.edu.turing.msbooking.model.enums.Role;
import com.az.edu.turing.msbooking.model.enums.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toUserEntity(CreateUserRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .status(UserStatus.valueOf(request.getStatus().toUpperCase()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();
    }

    public UserDto toUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .status(userEntity.getStatus())
                .role(String.valueOf(userEntity.getRole()))
                .build();
    }

    public UserEntity toUserEntity(UpdateUserRequest request) {
        return UserEntity.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .status(UserStatus.valueOf(request.getStatus().toUpperCase()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();
    }

    public UserEntity toUserEntity(UpdateUserRequest request, UserEntity entity) {
        entity.setEmail(request.getEmail());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setStatus(UserStatus.valueOf(request.getStatus().toUpperCase()));
        entity.setRole(Role.valueOf(request.getRole().toUpperCase()));
        return entity;
    }
}
