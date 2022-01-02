package com.example.sampleRestAPI.service;

import com.example.sampleRestAPI.dto.UserDTO;
import com.example.sampleRestAPI.dto.UserRegDTO;
import com.example.sampleRestAPI.entity.User;

import java.util.List;

public interface UserService {

    //사용자 목록 조회
    List<UserDTO> getUserList();

    //사용자 등록
    UserDTO saveUser(UserRegDTO userRegDTO);

    default UserDTO entityToDto(User entity) {

        UserDTO dto = UserDTO.builder()
                .userId(entity.getUserId())
                .name(entity.getName())
                .age(entity.getAge())
                .aplyDy(entity.getAplyDy())
                .build();

        return dto;
    }

}
