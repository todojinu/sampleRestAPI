package com.example.sampleRestAPI.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserBalanceDTO {

    private Long userId;

    private String name;

    private BigDecimal balance;

}
