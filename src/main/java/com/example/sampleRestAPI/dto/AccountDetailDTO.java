package com.example.sampleRestAPI.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailDTO {

    private String dpstDy;

    private Long acno;

    private int dpstSeq;

    private String dpstWthdrwYn;

    private BigDecimal dpstAmt;

}
