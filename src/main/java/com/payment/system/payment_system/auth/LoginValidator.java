package com.payment.system.payment_system.auth;

import com.payment.system.payment_system.exception.UnauthorizedException;
import com.payment.system.payment_system.model.User;
import com.payment.system.payment_system.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginValidator {

    private final UserRepository userRepository;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    public User validateAdmin() {
        var user = getCurrentUser();
        validateUser();
        if (user.isAdmin()) {
            return user;
        }
        throw new UnauthorizedException("User is not authorized");
    }

    public User validateUser() {
        var user = getCurrentUser();
        if (user == null) {
            throw new UnauthorizedException("User does not exist");
        }
        return user;
    }

}
