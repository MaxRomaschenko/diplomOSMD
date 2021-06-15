package com.politeh.edu.diplom.services;

import com.politeh.edu.diplom.model.User;
import com.politeh.edu.diplom.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public User findById(Long id){
        return userRepo.getOne(id);
    }
    public User findByEmail(String email){
        return userRepo.findUserByEmail(email);
    }

    public void deleteById(Long id){
        userRepo.deleteById(id);
    }


    public List<User> findAll(){
        return userRepo.findAll();
    }


}