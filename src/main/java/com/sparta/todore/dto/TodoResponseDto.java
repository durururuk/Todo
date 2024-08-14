package com.sparta.todore.dto;

import com.sparta.todore.entity.Todo;
import lombok.Getter;

import java.sql.Date;

@Getter
public class TodoResponseDto {
    private String contents;
    private String username;
    private Date date;
    private int id;
    private String message;

    public TodoResponseDto(Todo todo) {
        this.contents = todo.getContents();
        this.username = todo.getUsername();
        this.date = todo.getDate();
        this.id = todo.getId();
        this.message = todo.getMessage();
    }

    public TodoResponseDto(int id, String contents, String username, Date date) {
        this.id = id;
        this.contents = contents;
        this.username = username;
        this.date = date;
        this.message = "조회 성공";
    }
}