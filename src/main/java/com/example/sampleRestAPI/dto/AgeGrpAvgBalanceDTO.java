package com.example.sampleRestAPI.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AgeGrpAvgBalanceDTO {

    private Integer ageGrp;

    private BigDecimal avgBalance;

}
