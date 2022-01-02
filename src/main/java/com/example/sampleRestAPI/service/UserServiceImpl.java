package com.example.sampleRestAPI.service;

import com.example.sampleRestAPI.dto.UserDTO;
import com.example.sampleRestAPI.dto.UserRegDTO;
import com.example.sampleRestAPI.entity.User;
import com.example.sampleRestAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //사용자 목록 조회
    @Override
    public List<UserDTO> getUserList() {
        List<User> list = userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();

        for (User entity: list) {
            result.add(entityToDto(entity));
        }

        return result;
    }

    //사용자 등록
    @Override
    public UserDTO saveUser(UserRegDTO userRegDTO) {

        User user = User.builder()
                .name(userRegDTO.getName())
                .age(userRegDTO.getAge())
                .build();

        User entity = userRepository.save(user);

        UserDTO userDTO = entityToDto(entity);

        return userDTO;

    }

}
