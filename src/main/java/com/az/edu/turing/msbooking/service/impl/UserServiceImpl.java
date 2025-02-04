package com.az.edu.turing.msbooking.service.impl;


import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.domain.repository.UserRepository;
import com.az.edu.turing.msbooking.exception.AlreadyExistsException;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.UserMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;
import com.az.edu.turing.msbooking.model.enums.UserStatus;
import com.az.edu.turing.msbooking.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        checkIfFlightExists(createUserRequest.getEmail());
        UserEntity userEntity = userMapper.toUserEntity(createUserRequest);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.toUserDto(savedEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDto).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public UserDto updateUserById(Long id, UpdateUserRequest updateUserRequest) {
        checkIfFlightExists(updateUserRequest.getEmail());
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        userEntity.setFirstName(updateUserRequest.getFirstName());
        userEntity.setLastName(updateUserRequest.getLastName());
        userEntity.setEmail(updateUserRequest.getEmail());
        userEntity.setPhoneNumber(updateUserRequest.getPhoneNumber());
        userEntity.setStatus(UserStatus.valueOf(updateUserRequest.getStatus()));
        return userMapper.toUserDto(userRepository.save(userEntity));

    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User with id" + id + " not found");
        }
        //soft-delete
        userRepository.findById(id)
                .ifPresent(userEntity -> {
                    userEntity.setStatus(UserStatus.DELETED);
                    userRepository.save(userEntity);
                });

        //hard-delete
//        userRepository.deleteById(id);

    }

    private void checkIfFlightExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("User already exists with email: " + email);
        }
    }
}
