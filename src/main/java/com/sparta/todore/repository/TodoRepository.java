package com.sparta.todore.repository;

import com.sparta.todore.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo save(Todo todo) {
        //DB 저장
        //기본 키를 반환받기 위한 객체
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO todos (username, contents, password, date) VALUES(?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, todo.getUsername());
            preparedStatement.setString(2, todo.getContents());
            preparedStatement.setString(3, todo.getPassword());
            preparedStatement.setString(4, todo.getDate());
            return preparedStatement;
        }, keyHolder);

        //DB Insert 후 받아온 기본 키 확인
        Long id = keyHolder.getKey().longValue();
        todo.setId(id);

        return todo;
    }
}