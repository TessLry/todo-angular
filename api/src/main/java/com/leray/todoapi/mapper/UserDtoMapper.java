package com.leray.todoapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.leray.todoapi.dto.models.UserDto;
import com.leray.todoapi.entity.User;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto toDto(User user);

    List<UserDto> toListDto(List<User> users);
    
}
