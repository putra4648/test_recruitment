package com.pradana.test_recruitment.response;

import java.util.List;

import com.pradana.test_recruitment.dto.UserDto;

import lombok.Data;

@Data
public class UserResponse {
    private String status;
    private List<UserDto> results;
}
