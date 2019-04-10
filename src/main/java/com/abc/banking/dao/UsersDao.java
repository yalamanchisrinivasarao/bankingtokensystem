package com.abc.banking.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.banking.model.User;

import java.util.Optional;

public interface UsersDao extends JpaRepository<User, Integer> {
    Optional<User> findByFirstName(String firstname);
}
