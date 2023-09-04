package com.example.exjwt.service.impl;

import com.example.exjwt.common.CommonResponse;
import com.example.exjwt.config.security.JwtTokenProvider;
import com.example.exjwt.service.SignService;
import com.example.exjwt.data.dto.SignInResultDTO;
import com.example.exjwt.data.dto.SignUpResultDTO;
import com.example.exjwt.data.entity.User;
import com.example.exjwt.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    private final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDTO signUP(String id, String password, String name, String role) {
        LOGGER.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;

        if (role.equalsIgnoreCase("admin")) {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        } else {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignUpResultDTO signUpResultDTO = new SignInResultDTO();

        LOGGER.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if (!savedUser.getName().isEmpty()) {
            LOGGER.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signUpResultDTO);
        } else {
            LOGGER.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signUpResultDTO);
        }
        return signUpResultDTO;
    }


    @Override
    public SignInResultDTO signIn(String id, String password) throws RuntimeException {
        LOGGER.info("[getSignInResult] signDataHandler로 회원 정보 요청");
        User user = userRepository.getByUid(id);
        LOGGER.info("[getSignInResult] Id : {}", id);

        LOGGER.info("[getSignInResult] 패스워드 비교 수행");
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException();
        }
        LOGGER.info("[getSignResult] 패스워드 일치");

        LOGGER.info("[getSignInResult] SingInResultDTO 객체 생성");
        SignInResultDTO signInResultDTO = SignInResultDTO.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles()))
                .build();

        LOGGER.info("[getSignInResult] SignInResultDTO 객체에 값 주입");
        setSuccessResult(signInResultDTO);

        return signInResultDTO;
    }


    private void setSuccessResult(SignUpResultDTO result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDTO result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }

}