package com.pradana.test_recruitment.response;

import com.pradana.test_recruitment.dto.UserDto;

import lombok.Data;

@Data
public class RegisterResponse {
    private String message;
    private UserDto result;
}
