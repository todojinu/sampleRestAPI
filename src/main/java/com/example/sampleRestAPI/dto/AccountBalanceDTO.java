package com.example.sampleRestAPI.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceDTO {

    private Long acno;

    private BigDecimal avgBalance;

}
