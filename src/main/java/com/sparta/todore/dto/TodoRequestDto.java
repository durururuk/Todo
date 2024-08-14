package com.sparta.todore.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class TodoRequestDto {
    private String contents;
    private String username;
    private String password;
    private Integer id;
    private final String date = nowDate();

    /**
     *
     * @return 현재 시간 (월/일/시/분)
     */
    public static String nowDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
        return now.format(formatter);
    }
}
