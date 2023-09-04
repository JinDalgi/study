package com.example.exjwt.data.dto;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@ToString
@Builder
public class SignUpRequestDTO {
    private String id;
    private String password;
    private String name;
}
