package com.example.sampleRestAPI.controller;

import com.example.sampleRestAPI.dto.*;
import com.example.sampleRestAPI.service.AccountDetailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/AccountDetails")
@Slf4j
public class AccountDetailController {

    @Autowired
    private final AccountDetailService accountDetailService;

    @ApiOperation(value = "계좌내역 목록", notes = "입력받은 계좌의 계좌내역을 조회한다.")
    @GetMapping("/acno/{acno}")
    public ResponseEntity getAccountDetailList(@ApiParam(value = "계좌번호") @PathVariable("acno") Long acno) {

        log.info("getAccountDetailList...acno -> {}", acno);

        List<AccountDetailDTO> result = accountDetailService.getAccountAccountDetailList(acno);

        if (result.size() > 0) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("등록된 계좌가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "계좌내역 추가", notes = "입력받은 계좌의 계좌내역을 등록한다.")
    @PostMapping("/acno/{acno}")
    public ResponseEntity saveAccountDetail(@RequestBody AccountDetailRegDTO accountDetailRegDTO) {

        log.info("saveAccountDetail...accountDetailRegDTO -> {}", accountDetailRegDTO);

        AccountDetailDTO result = accountDetailService.saveAccountAccountDetail(accountDetailRegDTO);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("등록된 계좌가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "추가 REST API - 1)사용자 계좌별 예치금 조회", notes = "사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오")
    @GetMapping("/userId/{userId}")
    public ResponseEntity getUserAccountBalance(@ApiParam(value = "사용자ID") @PathVariable("userId") Long userId ) {

        log.info("getUserAccountBalance...userId -> {}", userId);

        List<AccountBalanceDTO> result = accountDetailService.getUserAccountBalanceList(userId);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("등록된 사용자가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "추가 REST API - 2)사용자 연령대별 평균 예치금 조회", notes = "사용자 나이대 별로, 평균 예치금을 출력하시오")
    @GetMapping("/userAgeGrpAvg")
    public ResponseEntity<List<AgeGrpAvgBalanceDTO>> getUserAgeGrpAvgBalance()
    {
        log.info("getUserAgeGrpAvgBalance...");

        List<AgeGrpAvgBalanceDTO> result = accountDetailService.getUserAgeGrpAvgBalanceList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "추가 REST API - 3)해당년도 예치금 총액 조회", notes = "년도를 입력받아, 해당년도의 예치금 총액을 출력하시오")
    @GetMapping("/yearTotBalance/{year}")
    public ResponseEntity<YearTotBalanceDTO> getYearTotBalance(@ApiParam(value = "조회년도") @PathVariable("year") String year)
    {
        log.info("getYearTotBalance...year -> {}", year);

        YearTotBalanceDTO result = accountDetailService.getYearTotBalance(year);

        log.info("===========================================");
        log.info(">> result...YearTotBalanceDTO -> {}", result);
        log.info("===========================================");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "추가 REST API - 4)고객별 예치금 조회", notes = "기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오")
    @GetMapping("/userBalance/")
    public ResponseEntity<List<UserBalanceDTO>> getUserBalanceList(@ApiParam(value = "조회시작일자") @RequestParam("strtDy") String strtDy,
                                                               @ApiParam(value = "조회시작일자") @RequestParam("endDy") String endDy)
    {
        log.info("getUserBalanceList...strtDy -> {}, endDy -> {}", strtDy, endDy);

        List<UserBalanceDTO> result = accountDetailService.getUserBalanceList(strtDy, endDy);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
