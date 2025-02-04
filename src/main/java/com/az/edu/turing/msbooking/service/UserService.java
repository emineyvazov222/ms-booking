package com.az.edu.turing.msbooking.service;


import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserRequest createUserRequest);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUserById(Long id, UpdateUserRequest updateUserRequest);

    void deleteById(Long id);


}
