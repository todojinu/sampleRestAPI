package com.example.sampleRestAPI.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailID implements Serializable {

    /* IdClass 를 사용하기 위해서는...
     * -식별자 클래스의 필드명과 엔티티에서 사용하는 식별자의 필드명 동일해야 함
     * -Serializable 인터페이스를 구현해야 함
     * -equals(), hashCode() 를 구현해야 함 -> @EqualsAndHashCode
     * -기본 생성자를 선언
     * -클래스의 접근 제한자는 public 이어야 함
     */

    private String dpstDy;  //입금일자

    private Long account;   //계좌

    private int dpstSeq;   //입금순번

}
