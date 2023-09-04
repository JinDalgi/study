package com.example.exjwt.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SignInRequestDTO {
    private String id;
    private String password;
}
