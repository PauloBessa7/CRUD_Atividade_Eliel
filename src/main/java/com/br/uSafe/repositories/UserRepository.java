package com.br.uSafe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.uSafe.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}

