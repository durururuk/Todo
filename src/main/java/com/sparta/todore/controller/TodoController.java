package com.sparta.todore.controller;

import com.sparta.todore.dto.TodoRequestDto;
import com.sparta.todore.dto.TodoResponseDto;
import com.sparta.todore.service.TodoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TodoController {
    public final JdbcTemplate jdbcTemplate;

    public TodoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/todos")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        TodoService todoService = new TodoService(jdbcTemplate);
        return todoService.createTodo(requestDto);
    }

    @GetMapping("/todos")
    public TodoResponseDto readTodobyId(@RequestBody TodoRequestDto requestDto) {
        TodoService todoService = new TodoService(jdbcTemplate);
        return todoService.readFoundTodo(requestDto);
    }

}
