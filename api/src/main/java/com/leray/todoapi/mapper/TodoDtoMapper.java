package com.leray.todoapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.leray.todoapi.dto.models.TodoDto;
import com.leray.todoapi.entity.Todo;

@Mapper(componentModel = "spring")
public interface TodoDtoMapper {

    TodoDto toDto(Todo todo);

    List<TodoDto> toListDto(List<Todo> todos);
    
}
