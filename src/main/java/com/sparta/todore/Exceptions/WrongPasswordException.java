package com.sparta.todore.Exceptions;

/**
 * Author : 신승재
 * Date : 24. 8. 20.
 * FileName : WrongPasswordException
 * Description :
 * =========================================================
 * DATE             AUTHOR              NOTE
 * ---------------------------------------------------------
 * 24. 8. 20.          신승재               최초 생성
 */
public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super("비밀번호가 올바르지 않습니다!");
    }
}
