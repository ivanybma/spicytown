package com.spicytown.service.Authentication;

import com.spicytown.model.entity.User;
import com.spicytown.service.User.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by cheyikung on 5/7/16.
 */

@Service
public class AuthenticationServices implements UserDetailsService {

    @Autowired
    UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userServices.findUserAccountByName(name);
//        if(user.isVerified()) {
            GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Arrays.asList(authority));
            return userDetails;
//        }
//        return null;
    }
}
