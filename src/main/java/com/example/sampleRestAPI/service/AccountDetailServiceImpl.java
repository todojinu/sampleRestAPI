package com.example.sampleRestAPI.service;

import com.example.sampleRestAPI.dto.*;
import com.example.sampleRestAPI.entity.AccountDetail;
import com.example.sampleRestAPI.entity.User;
import com.example.sampleRestAPI.repository.AccountDetailRepository;
import com.example.sampleRestAPI.repository.AccountRepository;
import com.example.sampleRestAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountDetailServiceImpl implements AccountDetailService {

    @Autowired
    private final AccountDetailRepository accountDetailRepository;

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<AccountDetailDTO> getAccountAccountDetailList(Long acno) {

        List<AccountDetail> list = accountDetailRepository.getAccountDetailsByAccount(acno);

        return list.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public AccountDetailDTO saveAccountAccountDetail(AccountDetailRegDTO accountDetailRegDTO) {

        AccountDetailDTO accountDetailDTO = null;

        if ( accountRepository.findById(accountDetailRegDTO.getAcno()).isPresent() ) {
            String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            Integer maxDpstSeq = accountDetailRepository.getMaxDpstSeqByDpstDyAndAcno(today, accountDetailRegDTO.getAcno());

            if (maxDpstSeq == null) maxDpstSeq = 0;

            accountDetailDTO = AccountDetailDTO.builder()
                    .dpstDy(today)
                    .acno(accountDetailRegDTO.getAcno())
                    .dpstSeq(++maxDpstSeq)
                    .dpstWthdrwYn(accountDetailRegDTO.getDpstWthdrwYn())
                    .dpstAmt(accountDetailRegDTO.getDpstAmt())
                    .build();

            log.info("accountDetailDTO... -> {}", accountDetailDTO);

            AccountDetail accountDetail = dtoToEntity(accountDetailDTO);
            accountDetail = accountDetailRepository.save(accountDetail);

            accountDetailDTO = entityToDto(accountDetail);
        }

        return accountDetailDTO;
    }

    @Override
    public List<AccountBalanceDTO> getUserAccountBalanceList(Long userId) {

        List<AccountBalanceDTO> result = null;

        if (userRepository.findById(userId).isPresent()) {

            List<Object[]> list = accountDetailRepository.getUserAccountBalance(userId);

            result = list.stream().map(objects -> AccountBalanceDTO.builder()
                    .acno((Long) objects[0])
                    .avgBalance((BigDecimal) objects[1])
                    .build()
            ).collect(Collectors.toList());
        }

        return result;
    }

    @Override
    public List<AgeGrpAvgBalanceDTO> getUserAgeGrpAvgBalanceList() {

        List<Object[]> list = accountDetailRepository.getUserAgeGroupAvgBalance();

        return list.stream().map(objects -> AgeGrpAvgBalanceDTO.builder()
                .ageGrp((Integer) objects[0])
                .avgBalance((BigDecimal) objects[1])
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public YearTotBalanceDTO getYearTotBalance(String year) {

        Object[] result = accountDetailRepository.getYearTotBalance(year);

        return YearTotBalanceDTO.builder().totBalance((BigDecimal) result[0]).build();
    }

    @Override
    public List<UserBalanceDTO> getUserBalanceList(String strtDy, String endDy) {

        List<Object[]> list = accountDetailRepository.getUserBalanceDescList(strtDy, endDy);

        return list.stream().map(objects -> {
            User user = (User)objects[0];

            return UserBalanceDTO.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .balance((BigDecimal) objects[1])
                    .build();

        }).collect(Collectors.toList());

    }

}
