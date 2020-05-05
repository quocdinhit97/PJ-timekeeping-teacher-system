package com.web.app.service;

import com.web.app.common.WebRole;
import com.web.app.entity.UserInfo;
import com.web.app.repository.UserInfoRepository;
import com.web.app.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository userInfoRepository;

    public void createUser (CreateUserRequest createUserRequest){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(createUserRequest.getUserName());
        userInfo.setEmail(createUserRequest.getEmail());
        userInfo.setBlock(false);
        userInfo.setFullName(createUserRequest.getFullName());
        userInfo.setRole(WebRole.USER);
        userInfo.setPassword(encoder.encode(createUserRequest.getPassword()));

        userInfoRepository.save(userInfo);
    }

    public UserInfo findOneByUsername(String username){
        return userInfoRepository.findByUsername(username);
    }

    public boolean isUserPresent(String username){
        return findOneByUsername(username) == null ? false : true;
    }
}
