package com.politeh.edu.diplom.repository;

import com.politeh.edu.diplom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByFirstName(String name);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    User findUserByEmail(String email);
}