package com.example.sampleRestAPI.dto;

import io.swagger.annotations.ApiParam;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailRegDTO {

    @ApiParam(value = "계좌번호", example = "100010")
    private Long acno;

    @ApiParam(value = "입출금 여부", example = "Y")
    private String dpstWthdrwYn;

    @ApiParam(value = "입출금 금액", example = "750000")
    private BigDecimal dpstAmt;

}
