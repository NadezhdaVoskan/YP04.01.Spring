package com.example.APIForFinalProject.Repositories;

import com.example.APIForFinalProject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
public interface UserRepository extends JpaRepository<User,Long>{
    User findUserByUsername (String username);
}
