package com.panshi.springbootjpathymeleaf.repository;


import com.panshi.springbootjpathymeleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    void deleteById(Long id);
}