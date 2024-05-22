package com.leray.todoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leray.todoapi.entity.User;
import com.leray.todoapi.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) throws Exception {
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isEmpty()) {
            throw new Exception("User not found with id" + id);
        }
        return optUser.get();
    }

    public void deleteUser(Long id) throws Exception {
        getUser(id);
        userRepository.deleteById(id);
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                // TODO custom exception
            }
        };
    }


}
