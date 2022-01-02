package com.example.sampleRestAPI.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;  //사용자 ID

    @Column(nullable = false)
    private String name;  //사용자명

    private int age;  //연령

    @CreatedDate
    @Column(length = 8)
    private String aplyDy;  //등록일자

    @PrePersist  // persist() 로 엔티티가 영속성컨텍스트에 저장되기 직전에 호출됨
    public void onPrePersist() {
        this.aplyDy = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
