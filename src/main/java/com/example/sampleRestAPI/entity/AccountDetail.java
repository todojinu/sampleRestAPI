package com.example.sampleRestAPI.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "account")
@IdClass(AccountDetailID.class)  //@IdClass 방식을 사용한 복합키 적용
public class AccountDetail {

    @Id
    @Column(length = 8)
    private String dpstDy;  //입금일자

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //외래키 매핑. name: 매핑할 외래키, referencedColumnName: 참조하는 테이블의 컬럼명
    @JoinColumn(name = "account_acno", referencedColumnName = "acno")
    private Account account;  //계좌

    @Id
    private int dpstSeq;  //입금순번

    @Column(length = 1, nullable = false)
    private String dpstWthdrwYn;  //입출금여부(Y:입금, N:출금)

    private BigDecimal dpstAmt;  //입금금액

}
