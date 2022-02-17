package com.huyeon.wd101.repository;

import com.huyeon.wd101.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String Email);

    Boolean existsByEmail(String Email);

    UserEntity findByEmailAndPassword(String email, String password);
}
