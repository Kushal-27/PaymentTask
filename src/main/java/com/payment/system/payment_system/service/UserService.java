package com.payment.system.payment_system.service;

import com.payment.system.payment_system.dto.UserDTO;
import com.payment.system.payment_system.model.User;

import java.util.List;

public interface UserService {

    User createUser(UserDTO userDTO);
    User getUserByUsername(String username);
    List<User> getAllUsers(User user);
}
