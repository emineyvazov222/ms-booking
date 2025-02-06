package com.az.edu.turing.msbooking.common;

import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;
import com.az.edu.turing.msbooking.model.enums.Role;
import com.az.edu.turing.msbooking.model.enums.UserStatus;

import java.util.List;


public interface UserTestConstant {

    Long USER_ID = 1L;
    String FIRST_NAME = "John";
    String LAST_NAME = "Doe";
    String EMAIL = "john.doe@gmail.com";
    String PHONE_NUMBER = "0123456789";


    UserEntity USER_ENTITY = UserEntity.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .email(EMAIL)
            .phoneNumber(PHONE_NUMBER)
            .status(UserStatus.ACTIVE)
            .role(Role.ADMIN)
            .build();


    List<UserEntity> USER_ENTITIES = List.of(USER_ENTITY);


    UserDto USER_DTO = UserDto.builder()
            .id(USER_ID)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .email(EMAIL)
            .phoneNumber(PHONE_NUMBER)
            .status(UserStatus.ACTIVE)
            .role("ADMIN")
            .build();


    CreateUserRequest CREATE_USER_REQUEST = CreateUserRequest.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .email(EMAIL)
            .phoneNumber(PHONE_NUMBER)
            .status("ACTIVE")
            .role("ADMIN")
            .build();


    UpdateUserRequest UPDATE_USER_REQUEST = UpdateUserRequest.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .email(EMAIL)
            .phoneNumber(PHONE_NUMBER)
            .status("ACTIVE")
            .role("ADMIN")
            .build();

}
