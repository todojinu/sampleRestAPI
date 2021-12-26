package com.example.sampleRestAPI.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;  //사용자

}
