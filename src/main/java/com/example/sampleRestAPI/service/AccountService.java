package com.example.sampleRestAPI.service;

import com.example.sampleRestAPI.dto.AccountDTO;
import com.example.sampleRestAPI.entity.Account;
import com.example.sampleRestAPI.entity.User;

import java.util.List;

public interface AccountService {

    //계좌목록 조회
    List<AccountDTO> getAccountList();

    //사용자 계좌목록 조회
    List<AccountDTO> getAccountListByUserId(Long userId);

    //사용자 계좌 저장
    AccountDTO saveUserAccount(Long userId);

    default Account dtoToEntity(AccountDTO dto) {

        User user = User.builder().userId(dto.getUserId()).build();

        Account entity = Account.builder()
                .acno(dto.getAcno())
                .user(user)
                .build();

        return entity;
    }

    default AccountDTO entityToDto(Account entity) {

        AccountDTO dto = AccountDTO.builder()
                .acno(entity.getAcno())
                .userId(entity.getUser().getUserId())
                .build();

        return dto;
    }

}
