package com.leray.todoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leray.todoapi.dto.models.TodoDto;
import com.leray.todoapi.dto.request.TodoRequest;
import com.leray.todoapi.entity.Todo;
import com.leray.todoapi.mapper.TodoDtoMapper;
import com.leray.todoapi.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoDtoMapper todoDtoMapper;

    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getTodos() {
        List<Todo> todos = todoService.getTodos();

        return ResponseEntity.ok(todoDtoMapper.toListDto(todos));
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) throws Exception {
        Todo todo = todoService.getTodo(id);

        return ResponseEntity.ok(todoDtoMapper.toDto(todo));
    }

    @PostMapping("/todo")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.createTodo(todoRequest);

        return ResponseEntity.ok(todoDtoMapper.toDto(todo));
    }
    
    @PutMapping("todo/{id}")
    public ResponseEntity<TodoDto> updateTodo (@PathVariable Long id, @RequestBody TodoRequest todoRequest) throws Exception {
        Todo todo = todoService.updateTodo(id, todoRequest);

        return ResponseEntity.ok(todoDtoMapper.toDto(todo));
    }

    @DeleteMapping("todo/{id}")
    public ResponseEntity<Void> deleteTodo (@PathVariable Long id) throws Exception {
        todoService.deleteTodo(id);

        return ResponseEntity.noContent().build();
    }
    
}
