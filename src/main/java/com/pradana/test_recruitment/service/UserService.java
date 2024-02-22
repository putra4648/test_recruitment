package com.pradana.test_recruitment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pradana.test_recruitment.dto.UserDto;
import com.pradana.test_recruitment.model.User;
import com.pradana.test_recruitment.repository.UserRepository;
import com.pradana.test_recruitment.request.RegisterRequest;
import com.pradana.test_recruitment.response.RegisterResponse;
import com.pradana.test_recruitment.response.UserResponse;

@Service
public class UserService {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private UserRepository repository;

    public ResponseEntity<UserResponse> getUsers() {
        List<UserDto> results = new ArrayList<>();
        UserResponse response = new UserResponse();

        repository.findAll().forEach(data -> {
            UserDto dto = new UserDto();
            dto.setUserid(data.getUserid());
            dto.setNamalengkap(data.getNamalengkap());
            dto.setStatus(data.getStatus());
            results.add(dto);
        });

        response.setStatus("Success");
        response.setResults(results);

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<RegisterResponse> registerUser(RegisterRequest body) {
        List<User> result = repository.findByUsername(body.getUsername());
        RegisterResponse response = null;

        if (!result.isEmpty()) {
            response = new RegisterResponse();
            response.setMessage("Username '" + body.getUsername() + "' is exist");
            return ResponseEntity.ok().body(response);
        } else {
            response = new RegisterResponse();
            response.setMessage("Success created user");

            User user = new User();
            user.setUserid(count.incrementAndGet());
            user.setUsername(body.getUsername());
            user.setPassword(body.getPassword());
            user.setNamalengkap(body.getNamalengkap());
            user.setStatus(body.getStatus());

            repository.save(user);

            UserDto dto = new UserDto();
            dto.setUserid(user.getUserid());
            dto.setStatus(user.getStatus());
            dto.setNamalengkap(user.getNamalengkap());

            response.setResult(dto);
            return ResponseEntity.ok().body(response);
        }
    }

    public ResponseEntity<UserResponse> deleteUser(Integer userid) {
        Optional<User> user = repository.findById(userid);
        UserResponse response = null;

        if (user.isPresent()) {
            User userExist = user.get();
            repository.delete(userExist);

            response = new UserResponse();
            response.setStatus("Success delete user with id " + userid);
            return ResponseEntity.ok().body(response);
        } else {
            // response = new UserResponse();
            // response.setStatus("User id with '" + userid + "' is not exist");
            return ResponseEntity.notFound().build();
        }
    }
}
