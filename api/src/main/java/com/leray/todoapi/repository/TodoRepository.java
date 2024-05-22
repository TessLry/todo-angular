package com.leray.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leray.todoapi.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
