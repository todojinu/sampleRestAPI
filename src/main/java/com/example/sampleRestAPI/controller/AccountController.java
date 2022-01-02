package com.example.sampleRestAPI.controller;

import com.example.sampleRestAPI.dto.AccountDTO;
import com.example.sampleRestAPI.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "계좌 목록", notes = "전체 계좌목록을 조회한다.")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDTO>> getAccountList() {

        log.info("getAccountList...");

        return new ResponseEntity<>(accountService.getAccountList(), HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 계좌 목록", notes = "특정 사용자의 계좌목록을 조회한다.")
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity getUserAccountList(@ApiParam(value = "사용자ID") @PathVariable("userId") Long userId) {

        log.info("getUserAccountList...userId -> {}", userId);

        List<AccountDTO> result = accountService.getAccountListByUserId(userId);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("등록된 사용자가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "계좌 추가", notes = "등록된 사용자의 계좌를 추가한다.")
    @PostMapping("/user/{userId}")
    public ResponseEntity saveAccount(@ApiParam(value = "사용자ID") @PathVariable("userId") Long userId) {

        log.info("saveAccount...userId -> {}", userId);

        AccountDTO result = accountService.saveUserAccount(userId);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("등록된 사용자가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

}
