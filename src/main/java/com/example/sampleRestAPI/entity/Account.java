package com.example.sampleRestAPI.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acno;  //계좌번호

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;  //사용자

}
