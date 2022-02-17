package com.huyeon.wd101.service;

import com.huyeon.wd101.model.UserEntity;
import com.huyeon.wd101.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity) {
        if (userEntity == null || userEntity.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String email = userEntity.getEmail();
        if (userRepository.existsByEmail(email)) {
            log.warn("Email already exist {}", email);
            throw new RuntimeException("Email already exist");
        }
        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(
            final String email,
            final String password,
            final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByEmail(email);
        if (originalUser != null && encoder.matches(password, originalUser.getPassword())) return originalUser;
        return null;
    }

}
