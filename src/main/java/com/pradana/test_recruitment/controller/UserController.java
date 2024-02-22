package com.pradana.test_recruitment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradana.test_recruitment.request.RegisterRequest;
import com.pradana.test_recruitment.response.RegisterResponse;
import com.pradana.test_recruitment.response.UserResponse;
import com.pradana.test_recruitment.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "/users")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest body) {
        return userService.registerUser(body);
    }

    @DeleteMapping(path = "/users")
    public ResponseEntity<UserResponse> deleteUser(@RequestBody Map<String, Object> body) {
        Integer userId = (Integer) body.get("userId");
        return userService.deleteUser(userId);
    }

}
