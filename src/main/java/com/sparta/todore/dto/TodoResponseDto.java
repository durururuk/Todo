package com.sparta.todore.dto;

import com.sparta.todore.entity.Todo;
import lombok.Getter;

@Getter
public class TodoResponseDto {
    private String contents;
    private String username;
    private String date;
    private Integer id;

    public TodoResponseDto(Todo todo) {
        this.contents = todo.getContents();
        this.username = todo.getUsername();
        this.date = todo.getDate();
        this.id = todo.getId();
    }
}