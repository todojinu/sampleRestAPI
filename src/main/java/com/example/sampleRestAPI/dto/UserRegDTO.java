package com.example.sampleRestAPI.dto;

import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegDTO {

    @ApiParam(value = "사용자명")
    private String name;

    @ApiParam(value = "연령")
    private int age;

}
