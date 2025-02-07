package com.az.edu.turing.msbooking.service;


import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.domain.repository.UserRepository;
import com.az.edu.turing.msbooking.exception.AlreadyExistsException;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.exception.UnauthorizedException;
import com.az.edu.turing.msbooking.mapper.UserMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;
import com.az.edu.turing.msbooking.model.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.az.edu.turing.msbooking.model.enums.Role.ADMIN;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(CreateUserRequest createUserRequest, String role) {
        checkIfAdmin(role);
        checkUserExists(createUserRequest.getEmail());
        return userMapper.toUserDto(userRepository.save(userMapper.toUserEntity(createUserRequest)));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDto).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserDto updateUserById(Long id, UpdateUserRequest updateUserRequest, String role) {
        checkIfAdmin(role);
        UserEntity updateUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.toUserDto(userRepository.save(userMapper
                .toUserEntity(updateUserRequest, updateUserEntity)));
    }

    public void deleteById(Long id, String role) {
        checkIfAdmin(role);
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

    private void checkUserExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("User already exists with email: " + email);
        }
    }

    private void checkIfAdmin(String role) {
        if (!ADMIN.name().equalsIgnoreCase(role)) {
            log.error("Unauthorized operation: User is not admin.");
            throw new UnauthorizedException("This operation can only be performed by administrators.");
        }
    }
}
