package com.example.sampleRestAPI.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    private String name;

    private int age;

    private String aplyDy;

}