package com.web.app.service;

import com.web.app.config.CustomUserDetail;
import com.web.app.entity.UserInfo;
import com.web.app.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoRepository.findByUsername(username);

        if(userInfo == null){
            throw new UsernameNotFoundException(String.format("User %s does not exist", username));
        }

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userInfo.getRole());
        return new CustomUserDetail(username, userInfo.getPassword(), Arrays.asList(authority));
    }
}
