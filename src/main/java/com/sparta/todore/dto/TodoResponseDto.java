package com.sparta.todore.dto;

import com.sparta.todore.entity.Todo;
import lombok.Getter;

import java.sql.Date;

@Getter
public class TodoResponseDto {
    private final String contents;
    private final String username;
    private final Date date;
    private final int id;
    private String message;

    public TodoResponseDto(Todo todo) {
        this.contents = todo.getContents();
        this.username = todo.getUsername();
        this.date = todo.getDate();
        this.id = todo.getId();
    }

    public TodoResponseDto(String message) {
        this.message = message;
        this.contents = null;
        this.username = null;
        this.date = null;
        this.id = 0;
    }

}