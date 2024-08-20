package com.sparta.todore.service;

import com.sparta.todore.Exceptions.WrongPasswordException;
import com.sparta.todore.dto.TodoRequestDto;
import com.sparta.todore.dto.TodoResponseDto;
import com.sparta.todore.entity.Todo;
import com.sparta.todore.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Component
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //일정 등록
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        //RequestDto >> Entity
        Todo todo = new Todo(requestDto);

        //DB 저장
        Todo saveTodo = todoRepository.saveTodo(todo);

        // Entity >> ResponseDto
        return new TodoResponseDto(saveTodo);
    }

    // id로 일정 단건 조회
    public TodoResponseDto readTodoById(int id) {
        //DB에서 찾아서 조회
        Todo foundTodo = todoRepository.readTodoById(id);

        // Entity >> ResponseDto
        return new TodoResponseDto(foundTodo);
    }

    // 일정 전체 조회
    public List<TodoResponseDto> readAllTodo() {
        List<Todo >todoList = todoRepository.readAllTodo();
        return entityListToResponseDtoList(todoList);
    }

    // 담당자명으로 일정 다건 조회
    public List<TodoResponseDto> readTodoByUsername(String username) {
        List<Todo>todoList = todoRepository.readTodoByUsername(username);
        return entityListToResponseDtoList(todoList);
    }

    // 등록/수정일자로 일정 다건 조회
    public List<TodoResponseDto> readTodoByDate(Date date) {
        List<Todo>todoList = todoRepository.readTodoByDate(date);
        return entityListToResponseDtoList(todoList);
    }

    // 담당자명, 등록/수정일자로 일정 다건 조회
    public List<TodoResponseDto> readTodoByUsernameAndDate(String username, Date date) {
        List<Todo>todoList = todoRepository.readTodoByUsernameAndDate(username, date);
        return entityListToResponseDtoList(todoList);
    }

    // id로 일정 수정
    public TodoResponseDto updateTodo(TodoRequestDto requestDto) {
        try{
            Todo todo = new Todo(requestDto);
            Todo updatedTodo = todoRepository.updateTodo(todo);
            return new TodoResponseDto(updatedTodo);
        } catch(WrongPasswordException e) {
            return new TodoResponseDto(e.getMessage());
        }

    }

    // id로 일정 삭제
    public void deleteTodo(int id, String password) {
        try{
            todoRepository.deleteTodo(id,password);
            System.out.println("삭제가 완료되었습니다.");
        } catch(WrongPasswordException e) {
            e.getMessage();
        }

    }

    /**
     *  Entity 리스트 -> ResponseDto 리스트
     * @param todoList Entity 리스트
     * @return ResponseDto 리스트
     */
    public List<TodoResponseDto> entityListToResponseDtoList(List<Todo> todoList) {
        List<TodoResponseDto> responseDtos = new ArrayList<>();
        for(Todo todo : todoList) {
            responseDtos.add(new TodoResponseDto(todo));
        }
        return responseDtos;
    }
}

