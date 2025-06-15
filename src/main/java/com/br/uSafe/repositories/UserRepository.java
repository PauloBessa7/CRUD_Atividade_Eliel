package com.br.uSafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.uSafe.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}

