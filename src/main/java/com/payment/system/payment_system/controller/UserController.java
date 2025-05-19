package com.payment.system.payment_system.controller;

import com.payment.system.payment_system.auth.LoginValidator;
import com.payment.system.payment_system.dto.UserDTO;
import com.payment.system.payment_system.model.User;
import com.payment.system.payment_system.service.UserService;
import com.payment.system.payment_system.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final LoginValidator loginValidator;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        loginValidator.validateUser();
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var currentUser = loginValidator.validateUser();
        List<User> users = userService.getAllUsers(currentUser);
        return ResponseEntity.ok(users);
    }
}
