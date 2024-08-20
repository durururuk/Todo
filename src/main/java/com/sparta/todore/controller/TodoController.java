package com.sparta.todore.controller;

import com.sparta.todore.dto.TodoRequestDto;
import com.sparta.todore.dto.TodoResponseDto;

import com.sparta.todore.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    @GetMapping("/id")
    public TodoResponseDto readTodo(@RequestParam("id") int id) {
        return todoService.readTodoById(id);
    }

    @GetMapping
    public List<TodoResponseDto> readTodos(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "date", required = false) Date date) {

        if (username != null && date != null) {
            return todoService.readTodoByUsernameAndDate(username, date);
        } else if (username != null) {
            return todoService.readTodoByUsername(username);
        } else if (date != null) {
            return todoService.readTodoByDate(date);
        } else {
            return todoService.readAllTodo();
        }
    }

    @PutMapping
    public TodoResponseDto updateTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.updateTodo(requestDto);
    }

    @DeleteMapping
    public void deleteTodo(@RequestParam("id") int id, @RequestParam("password") String password) {
        todoService.deleteTodo(id,password);
    }


//    @PutMapping("/todos")
//    public TodoResponseDto updateTodo(@RequestBody TodoRequestDto requestDto) {
//        return todoService.updateTodo(requestDto);
//    }
//
//    @DeleteMapping("/todos")
//    public TodoResponseDto deleteTodo(@RequestBody TodoRequestDto requestDto) {
//        return todoService.deleteTodo(requestDto);
//    }

}
