package com.example.exjwt.service;

import com.example.exjwt.data.dto.SignInResultDTO;
import com.example.exjwt.data.dto.SignUpResultDTO;

public interface SignService {

    SignUpResultDTO signUP(String id, String password, String name, String role);

    SignInResultDTO signIn(String id, String password) throws RuntimeException;
}
