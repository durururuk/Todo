package com.sparta.todore.controller;

import com.sparta.todore.dto.TodoRequestDto;
import com.sparta.todore.dto.TodoResponseDto;

import com.sparta.todore.service.TodoService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> readTodo(@RequestBody TodoRequestDto requestDto) {
        TodoService todoService = new TodoService(jdbcTemplate);
        if (requestDto.getId() != 0) {
            return ResponseEntity.ok(todoService.readTodoById(requestDto));
        } else {
            return ResponseEntity.ok(todoService.readTodoElse(requestDto));
        }
    }

    @PutMapping("/todos")
    public TodoResponseDto updateTodo(@RequestBody TodoRequestDto requestDto) {
        TodoService todoService = new TodoService(jdbcTemplate);
        return todoService.updateTodo(requestDto);
    }

    @DeleteMapping("/todos")
    public TodoResponseDto deleteTodo(@RequestBody TodoRequestDto requestDto) {
        TodoService todoService = new TodoService(jdbcTemplate);
        return todoService.deleteTodo(requestDto);
    }

}
