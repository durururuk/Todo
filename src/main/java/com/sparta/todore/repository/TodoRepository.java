package com.sparta.todore.repository;

import com.sparta.todore.Exceptions.WrongPasswordException;
import com.sparta.todore.dto.TodoRequestDto;
import com.sparta.todore.dto.TodoResponseDto;
import com.sparta.todore.entity.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public class TodoRepository {
    @PersistenceContext
    EntityManager em;

    //일정 저장
    @Transactional
    public Todo saveTodo(Todo todo) {
        em.persist(todo);
        return todo;
    }

    //id로 일정 조회
    public Todo readTodoById(int id) {
        return em.find(Todo.class, id);
    }

    //전체 일정 조회
    public List<Todo> readAllTodo() {
        return em.createQuery("SELECT t from Todo t",  Todo.class).getResultList();
    }

    //담당자명으로 일정 조회
    public List<Todo> readTodoByUsername(String username) {
        return em.createQuery("SELECT t from Todo t WHERE t.username = :username", Todo.class)
                .setParameter("username", username)
                .getResultList();
    }

    // 등록/수정일자로 일정 다건 조회
    public List<Todo> readTodoByDate(Date date) {
        return em.createQuery("SELECT t from Todo t WHERE t.date = :date", Todo.class)
                .setParameter("date", date)
                .getResultList();
    }

    // 담당자명, 등록/수정일자로 일정 다건 조회
    public List<Todo> readTodoByUsernameAndDate(String username, Date date) {
        return em.createQuery("SELECT t from Todo t WHERE t.username = :username and t.date = :date", Todo.class)
                .setParameter("username",username)
                .setParameter("date", date)
                .getResultList();
    }

    //id값으로 담당자명, 일정 내용 수정
    @Transactional
    public Todo updateTodo(Todo todo) throws WrongPasswordException {
        Todo foundTodo = em.find(Todo.class, todo.getId());
        if (foundTodo.getPassword().equals(todo.getPassword())) {
            foundTodo.setUsername(todo.getUsername());
            foundTodo.setContents(todo.getContents());
            return foundTodo;
        } else {
            throw new WrongPasswordException();
        }

    }

    //id값으로 일정 삭제
    @Transactional
    public void deleteTodo(int id, String password) throws WrongPasswordException {
        Todo foundTodo = em.find(Todo.class, id);
        if(foundTodo.getPassword().equals(password)){
            System.out.println("삭제가 완료되었습니다.");
            em.remove(foundTodo);
        } else {
            throw new WrongPasswordException();
        }
    }
}
