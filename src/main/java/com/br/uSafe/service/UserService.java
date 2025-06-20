package com.br.uSafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.uSafe.model.User;
import com.br.uSafe.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){ // 
        return userRepository.findAll();
    }

    public boolean deleteUserId(Long id){ //
        userRepository.deleteById(id);
        return true;
    }

    public User createUser(User user){ //
        return userRepository.save(user);
    }

    public User updateUser(User user){ //
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
