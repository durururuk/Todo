package com.sparta.todore.service;

import com.sparta.todore.dto.TodoRequestDto;
import com.sparta.todore.dto.TodoResponseDto;
import com.sparta.todore.entity.Todo;
import com.sparta.todore.repository.TodoRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TodoService {
    private final JdbcTemplate jdbcTemplate;

    public TodoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        //RequestDto >> Entity
        Todo todo = new Todo(requestDto);

        //DB 저장
        TodoRepository todoRepository = new TodoRepository(jdbcTemplate);
        Todo saveTodo = todoRepository.save(todo);

        // Entity >> ResponseDto
        return new TodoResponseDto(saveTodo);
    }

    public TodoResponseDto readTodoById(TodoRequestDto requestDto) {
        //RequestDto >> Entity
        Todo todo = new Todo(requestDto);

        //DB에서 찾아서 조회
        TodoRepository todoRepository = new TodoRepository(jdbcTemplate);
        Todo foundTodo = todoRepository.readTodoById(todo.getId());

        // Entity >> ResponseDto
        return new TodoResponseDto(foundTodo);
    }

    public List<TodoResponseDto> readTodoElse(TodoRequestDto requestDto) {
        //RequestDto >> Entity
        Todo todo = new Todo(requestDto);

        //DB 조회
        TodoRepository todoRepository = new TodoRepository(jdbcTemplate);

        if (todo.getUsername() != null) {
            return todoRepository.readTodoByUsername(todo.getUsername());
        } else if (todo.getDate() != null) {
            return todoRepository.readTodoByDate(todo.getDate());
        } else if (todo.getUsername() != null && todo.getDate() != null) {
            return todoRepository.readTodoByDateAndUsername(todo.getDate(), todo.getUsername());
        } else {
            return todoRepository.readAllTodo();
        }
    }
}

