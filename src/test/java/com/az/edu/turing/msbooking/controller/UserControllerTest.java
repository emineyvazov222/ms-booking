package com.az.edu.turing.msbooking.controller;


import com.az.edu.turing.msbooking.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.Set;


import static com.az.edu.turing.msbooking.common.UserTestConstant.USER_DTO;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @Test
    void getBookingById_ShouldReturnSuccess() throws Exception {
        given(userService.getUserById(1L)).willReturn(USER_DTO);

        mockMvc.perform(get("/api/v1/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(USER_DTO)))
                .andDo(print());

        then(userService).should(times(1)).getUserById(1L);
    }

    @Test
    void getAll_Should_ReturnSuccess() throws Exception {
        given(userService.getAllUsers()).willReturn(List.of(USER_DTO));

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Set.of(USER_DTO))))
                .andDo(MockMvcResultHandlers.print());

        then(userService).should(times(1)).getAllUsers();
    }

    @Test
    void delete_Should_ReturnSuccess() throws Exception {
        willDoNothing().given(userService).deleteById(1L, "ADMIN");
        mockMvc.perform(delete("/api/v1/users/{id}", 1L)
                        .header("role", "ADMIN"))
                .andExpect(status().isNoContent());
        verify(userService, times(1)).deleteById(1L, "ADMIN");
    }


}
