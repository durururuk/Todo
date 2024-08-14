package com.sparta.todore.repository;

import com.sparta.todore.dto.TodoResponseDto;
import com.sparta.todore.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo saveTodo(Todo todo) {
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
            preparedStatement.setDate(4, todo.getDate());
            return preparedStatement;
        }, keyHolder);

        //DB Insert 후 받아온 기본 키 확인
        Integer id = keyHolder.getKey().intValue();
        todo.setId(id);
        todo.setMessage("등록 성공");
        return todo;
    }

    //id로 일정 조회
    public Todo readTodoById(int id) {
        String sql = "SELECT * FROM todos WHERE id = ?";
        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Todo todo = new Todo();
                todo.setUsername(resultSet.getString("username"));
                todo.setContents(resultSet.getString("contents"));
                todo.setDate(resultSet.getDate("date"));
                todo.setId(resultSet.getInt("id"));
                return todo;
            } else {
                return null;
            }
        }, id);
    }

    //전체 일정 조회
    public List<TodoResponseDto> readAllTodo() {
        // DB 조회
        String sql = "SELECT * FROM todos";

        return jdbcTemplate.query(sql, new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                Date date = rs.getDate("date");
                return new TodoResponseDto(id, username, contents, date);
            }
        });
    }

    // username으로 조회
    public List<TodoResponseDto> readTodoByUsername(String username) {
        String sql = "SELECT * FROM todos WHERE username = ?";

        return jdbcTemplate.query(sql,new Object[]{username}, new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                Date date = rs.getDate("date");
                return new TodoResponseDto(id, username, contents, date);
            }
        });
    }

    // date로 조회
    public List<TodoResponseDto> readTodoByDate(Date date) {
        String sql = "SELECT * FROM todos WHERE date = ?";

        return jdbcTemplate.query(sql,new Object[]{date}, new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                Date date = rs.getDate("date");
                return new TodoResponseDto(id, username, contents, date);
            }
        });
    }

    // username과 date로 조회
    public List<TodoResponseDto> readTodoByDateAndUsername(Date date, String username) {
        String sql = "SELECT * FROM todos WHERE date = ? and username = ?";

        return jdbcTemplate.query(sql, new Object[]{date,username}, new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                Date date = rs.getDate("date");
                return new TodoResponseDto(id, username, contents, date);
            }
        });
    }

    public void updateTodoById(Todo todo) {
        String sql = "SELECT password FROM todos WHERE id = ?";
        String dbPassword = jdbcTemplate.queryForObject(sql, new Object[]{todo.getId()}, String.class);

        // 비밀번호가 맞는지 확인
        if (dbPassword.equals(todo.getPassword())) {
            // 비밀번호가 맞으면 업데이트 실행
            sql = "UPDATE todos SET username = ?, contents = ? WHERE id = ?";
            jdbcTemplate.update(sql, todo.getUsername(), todo.getContents(), todo.getId());
            todo.setMessage("수정 성공");
        } else {
            todo.setMessage("비밀번호가 틀렸습니다.");
        }
    }

    public void deleteTodoById(Todo todo) {
        String sql = "SELECT password FROM todos WHERE id = ?";
        String dbPassword = jdbcTemplate.queryForObject(sql, new Object[]{todo.getId()}, String.class);

        // 비밀번호가 맞는지 확인
        if (dbPassword.equals(todo.getPassword())) {
            // 비밀번호가 맞으면 삭제
            sql = "DELETE FROM todos WHERE id = ?";
            jdbcTemplate.update(sql, todo.getId());
            todo.setMessage("삭제 성공");
        } else {
            todo.setMessage("비밀번호가 틀렸습니다.");
        }
    }


}
