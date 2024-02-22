package com.pradana.test_recruitment.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String namalengkap;
    private String username;
    private String password;
    private Character status;
}
