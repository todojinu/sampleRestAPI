package com.example.sampleRestAPI.service;

import com.example.sampleRestAPI.dto.*;
import com.example.sampleRestAPI.entity.Account;
import com.example.sampleRestAPI.entity.AccountDetail;

import java.util.List;

public interface AccountDetailService {

    //계좌 계좌내역 목록조회
    List<AccountDetailDTO> getAccountAccountDetailList(Long acno);

    //계좌 계좌내역 저장
    AccountDetailDTO saveAccountAccountDetail(AccountDetailRegDTO accountDetailRegDTO);

    //사용자 계좌 예치금 조회
    List<AccountBalanceDTO> getUserAccountBalanceList(Long userId);

    //사용자 연령대별 평균 예치금 조회
    List<AgeGrpAvgBalanceDTO> getUserAgeGrpAvgBalanceList();

    //해당년도 예치금 총액 조회
    YearTotBalanceDTO getYearTotBalance(String year);

    //고객 예치금 목록 조회
    List<UserBalanceDTO> getUserBalanceList(String strtDy, String endDy);

    default AccountDetailDTO entityToDto(AccountDetail entity) {
        AccountDetailDTO dto = AccountDetailDTO.builder()
                .dpstDy(entity.getDpstDy())
                .acno(entity.getAccount().getAcno())
                .dpstSeq(entity.getDpstSeq())
                .dpstWthdrwYn(entity.getDpstWthdrwYn())
                .dpstAmt(entity.getDpstAmt())
                .build();

        return dto;
    }

    default AccountDetail dtoToEntity(AccountDetailDTO dto) {
        Account account = Account.builder().acno(dto.getAcno()).build();

        AccountDetail entity = AccountDetail.builder()
                .dpstDy(dto.getDpstDy())
                .account(account)
                .dpstSeq(dto.getDpstSeq())
                .dpstWthdrwYn(dto.getDpstWthdrwYn())
                .dpstAmt(dto.getDpstAmt())
                .build();

        return entity;
    }

}
