package com.sparta.todore.dto;

import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
public class TodoRequestDto {
    private String contents;
    private String username;
    private String password;
    private int id;
    private final Date date = currentSqlDate();

    public static Date currentSqlDate() {
        LocalDate localDate = LocalDate.now();
        return Date.valueOf(localDate);
    }
}
