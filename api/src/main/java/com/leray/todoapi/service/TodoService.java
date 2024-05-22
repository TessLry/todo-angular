package com.leray.todoapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leray.todoapi.dto.request.TodoRequest;
import com.leray.todoapi.entity.Todo;
import com.leray.todoapi.repository.TodoRepository;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodo(Long idTodo) throws Exception{
        Optional<Todo> optionalTodo = todoRepository.findById(idTodo);
        if(optionalTodo.isEmpty()) {
            throw new Exception("Todo not found with id" + idTodo);
        } 

        return optionalTodo.get();
    }

    public Todo createTodo(TodoRequest todoRequest) {
        Todo todo = new Todo();

        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setStatus(todoRequest.getStatus());
        todo.setCreated_at(new Date());
        
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long idTodo, TodoRequest todoRequest) throws Exception {
        Optional<Todo> optionalTodo = todoRepository.findById(idTodo);
        if(optionalTodo.isEmpty()) {
            throw new Exception("Todo not found with id" + idTodo);
        } 

        Todo todo = optionalTodo.get();

        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setStatus(todoRequest.getStatus());
        todo.setUpdated_at(new Date());
        
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long idTodo) throws Exception {
        Optional<Todo> optionalTodo = todoRepository.findById(idTodo);
        if(optionalTodo.isEmpty()) {
            throw new Exception("Todo not found with id" + idTodo);
        }

        todoRepository.deleteById(optionalTodo.get().getId());
    }
    
}
