package com.example.sampleRestAPI.service;

import com.example.sampleRestAPI.dto.AccountDTO;
import com.example.sampleRestAPI.entity.Account;
import com.example.sampleRestAPI.entity.User;
import com.example.sampleRestAPI.repository.AccountRepository;
import com.example.sampleRestAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public List<AccountDTO> getAccountList() {

        List<Account> list = accountRepository.findAll();

        return list.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> getAccountListByUserId(Long userId) {

        List<Account> list = null;

        if ( userRepository.findById(userId).isPresent() ) {
            User user = User.builder()
                    .userId(userId)
                    .build();
            list = accountRepository.getAccountsByUserOrderByUserUserId(user);
        }

        return list==null ? null : list.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO saveUserAccount(Long userId) {

        AccountDTO accountDTO = null;

        if ( userRepository.findById(userId).isPresent() ) {
            User user = User.builder().userId(userId).build();
            Account account = Account.builder().user(user).build();

            account = accountRepository.save(account);

            accountDTO = entityToDto(account);
        }

        return accountDTO;
    }

}
