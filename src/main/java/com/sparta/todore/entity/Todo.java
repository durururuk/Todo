package com.sparta.todore.entity;

import com.sparta.todore.dto.TodoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Todo {
    private String contents;
    private String username;
    private String password;
    private String date;
    private long id;

    public Todo(TodoRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}