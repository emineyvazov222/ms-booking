package com.az.edu.turing.msbooking.service;


import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;

public interface UserService {

    UserDto createUser(CreateUserRequest createUserRequest);

    void deleteById(Long id);
}
