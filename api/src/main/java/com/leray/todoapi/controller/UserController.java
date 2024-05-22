package com.leray.todoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leray.todoapi.dto.models.UserDto;
import com.leray.todoapi.entity.User;
import com.leray.todoapi.mapper.UserDtoMapper;
import com.leray.todoapi.service.UserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getUsers();

        return ResponseEntity.ok(userDtoMapper.toListDto(users));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws Exception {
        User user = userService.getUser(id);

        return ResponseEntity.ok(userDtoMapper.toDto(user));
    }
   
    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
    
}
