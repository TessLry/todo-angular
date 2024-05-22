package com.leray.todoapi.dto.models;

import java.util.Date;

import com.leray.todoapi.entity.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Date created_at;
    private Date updated_at;
    
}
