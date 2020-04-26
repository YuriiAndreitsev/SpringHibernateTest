package com.repository;


import com.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <Users, Integer> {

    public Users getUserByEmail(String email);
}
