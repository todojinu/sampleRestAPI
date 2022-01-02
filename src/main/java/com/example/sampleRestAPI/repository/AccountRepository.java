package com.example.sampleRestAPI.repository;

import com.example.sampleRestAPI.entity.Account;
import com.example.sampleRestAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //사용자 계좌목록 조회
    List<Account> getAccountsByUserOrderByUserUserId(User user);

}
