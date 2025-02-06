package com.az.edu.turing.msbooking.model.dto.response;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserStatus status;
    private String role;
}
