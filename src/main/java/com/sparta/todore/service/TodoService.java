package com.sparta.todore.service;

import com.sparta.todore.dto.TodoRequestDto;
import com.sparta.todore.dto.TodoResponseDto;
import com.sparta.todore.entity.Todo;
import com.sparta.todore.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        //RequestDto >> Entity
        Todo todo = new Todo(requestDto);

        //DB 저장
        Todo saveTodo = todoRepository.saveTodo(todo);

        // Entity >> ResponseDto
        return new TodoResponseDto(saveTodo);
    }

    public TodoResponseDto readTodoById(TodoRequestDto requestDto) {
        //RequestDto >> Entity
        Todo todo = new Todo(requestDto);

        //DB에서 찾아서 조회
        Todo foundTodo = todoRepository.readTodoById(todo.getId());

        // Entity >> ResponseDto
        return new TodoResponseDto(foundTodo);
    }

    public List<TodoResponseDto> readTodoElse(TodoRequestDto requestDto) {
        //RequestDto >> Entity
        Todo todo = new Todo(requestDto);

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

    //id값으로 담당자명, 일정 내용 수정
    public TodoResponseDto updateTodo(TodoRequestDto requestDto) {
        //RequestDto >> Entity
        Todo todo = new Todo(requestDto);

        //DB 저장
        todoRepository.updateTodoById(todo);

        // Entity >> ResponseDto
        return new TodoResponseDto(todo);
    }

    //id값으로 일정 삭제
    public TodoResponseDto deleteTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);

        todoRepository.deleteTodoById(todo);

        return new TodoResponseDto(todo);
    }
}

