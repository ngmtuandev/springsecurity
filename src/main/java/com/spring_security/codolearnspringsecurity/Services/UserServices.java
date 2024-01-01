package com.spring_security.codolearnspringsecurity.Services;

import com.spring_security.codolearnspringsecurity.Entity.AppicationUser;
import com.spring_security.codolearnspringsecurity.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServices implements UserDetailsService {

    /*Ma hoa mat khau*/
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user detail servies");
        if (!username.equals("Manhtuan"))
            throw new UsernameNotFoundException("not manhtuan");
        Set<Role> roles = new HashSet<>();

        roles.add(new Role(1, "USER"));

        return new AppicationUser(1, "Manhtuan", encoder.encode("password"), roles);

    }
}
