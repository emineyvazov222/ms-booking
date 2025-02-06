package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.domain.repository.UserRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.UserMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;

import static com.az.edu.turing.msbooking.common.UserTestConstant.*;

import com.az.edu.turing.msbooking.model.enums.UserStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Spy
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void create_ShouldReturnSuccess() {
        when(userMapper.toUserEntity(any(CreateUserRequest.class))).thenReturn(USER_ENTITY);
        when(userRepository.save(any(UserEntity.class))).thenReturn(USER_ENTITY);
        when(userMapper.toUserDto(any(UserEntity.class))).thenReturn(USER_DTO);

        UserDto result = userService.createUser(CREATE_USER_REQUEST, CREATE_USER_REQUEST.getRole());

        assertNotNull(result);
        assertEquals(USER_DTO.getId(), result.getId());
        assertEquals(USER_DTO.getEmail(), result.getEmail());

        verify(userMapper, times(1)).toUserEntity(CREATE_USER_REQUEST);
        verify(userRepository, times(1)).save(USER_ENTITY);
        verify(userMapper, times(1)).toUserDto(USER_ENTITY);
    }

    @Test
    void getAll_ShouldReturnSuccess() {
        when(userRepository.findAll()).thenReturn((USER_ENTITIES));
        when(userMapper.toUserDto(any(UserEntity.class))).thenReturn(USER_DTO);

        List<UserDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.getFirst().getFirstName());

        verify(userRepository).findAll();
        verify(userMapper, times(1)).toUserDto(any(UserEntity.class));
    }

    @Test
    void getUserById_ShouldReturnSuccess() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.toUserDto(USER_ENTITY)).thenReturn(USER_DTO);

        UserDto result = userService.getUserById(USER_ID);

        assertNotNull(result);
        assertEquals(USER_ID, result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());

        verify(userRepository).findById(USER_ID);
        verify(userMapper).toUserDto(USER_ENTITY);
    }

    @Test
    void getUserById_ShouldReturnNotFound() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.getUserById(USER_ID));
        assertEquals("User not found", exception.getMessage());

        verify(userRepository).findById(USER_ID);
        verify(userMapper, never()).toUserDto(any(UserEntity.class));

    }

    @Test
    void updateUser_ShouldReturnSuccess() {
        when(userRepository.existsById(USER_ID)).thenReturn(true);
        when(userRepository.save(any(UserEntity.class))).thenReturn(USER_ENTITY);
        when(userMapper.toUserDto(any(UserEntity.class))).thenReturn(USER_DTO);
        when(userMapper.toUserEntity(any(UpdateUserRequest.class))).thenReturn(USER_ENTITY);

        UserDto result = userService.updateUserById(USER_ID, UPDATE_USER_REQUEST, UPDATE_USER_REQUEST.getRole());

        assertNotNull(result);
        assertEquals(USER_ID, result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());

        verify(userRepository).existsById(USER_ID);
        verify(userRepository).save(any(UserEntity.class));
        verify(userMapper).toUserDto(any(UserEntity.class));
        verify(userMapper).toUserEntity(any(UpdateUserRequest.class));

    }

    @Test
    void updateUserById_ShouldUserNotFound() {
        when(userRepository.existsById(USER_ID)).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> userService.updateUserById(USER_ID, UPDATE_USER_REQUEST, UPDATE_USER_REQUEST.getRole()));
        assertEquals("User with id " + USER_ID + " not found", exception.getMessage());

        verify(userRepository).existsById(USER_ID);
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void deleteUserById_ShouldReturnSuccess() {
        when(userRepository.existsById(USER_ID)).thenReturn(true);
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));

        userService.deleteById(USER_ID, "ADMIN");
        assertEquals(UserStatus.DELETED, USER_ENTITY.getStatus());

        verify(userRepository).existsById(USER_ID);
        verify(userRepository).findById(USER_ID);
        verify(userRepository).save(USER_ENTITY);
    }
}
