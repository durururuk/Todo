package com.sparta.todore.controller;

import com.sparta.todore.dto.TodoRequestDto;
import com.sparta.todore.dto.TodoResponseDto;

import com.sparta.todore.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> readTodo(@RequestBody TodoRequestDto requestDto) {
        if (requestDto.getId() != 0) {
            return ResponseEntity.ok(todoService.readTodoById(requestDto));
        } else {
            return ResponseEntity.ok(todoService.readTodoElse(requestDto));
        }
    }

    @PutMapping("/todos")
    public TodoResponseDto updateTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.updateTodo(requestDto);
    }

    @DeleteMapping("/todos")
    public TodoResponseDto deleteTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.deleteTodo(requestDto);
    }

}
