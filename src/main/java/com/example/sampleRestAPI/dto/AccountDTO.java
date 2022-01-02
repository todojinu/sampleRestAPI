package com.example.sampleRestAPI.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long acno;

    private Long userId;

}
