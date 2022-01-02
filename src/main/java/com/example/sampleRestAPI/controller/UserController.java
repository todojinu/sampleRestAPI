package com.example.sampleRestAPI.controller;

import com.example.sampleRestAPI.dto.UserDTO;
import com.example.sampleRestAPI.dto.UserRegDTO;
import com.example.sampleRestAPI.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "사용자 목록", notes = "전체 사용자 목록을 조회한다.")
    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getUserList() {

        log.info("getUserList...");

        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 추가", notes = "사용자를 등록한다.")
    @PostMapping("")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserRegDTO userRegDTO) {

        log.info("saveUser...userRegDTO -> {}", userRegDTO);

        UserDTO result = userService.saveUser(userRegDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
