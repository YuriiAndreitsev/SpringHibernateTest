package com.service;


import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.UserRepository;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private static final String SALT = "1234";

    public Users addUser(Users user) {
        return userRepository.save(user);
    }

    public Users getUserById(int id) {
        return userRepository.getOne(id);
    }

    public Users getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    public boolean checkEmail(String email) {
        boolean ifExist = false;
        if(userRepository.getUserByEmail(email)!=null) {
            if (!userRepository.getUserByEmail(email).getEmail().isEmpty()) {
                ifExist = true;
            }
        }
        return ifExist;
    }

    public String getHash(String pass) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(StandardCharsets.UTF_8.encode(pass + SALT));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return (String.format("%032x", new BigInteger(md.digest())));
    }
}
