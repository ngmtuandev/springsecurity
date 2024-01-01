package com.spring_security.codolearnspringsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Danh dau day la 1 lop cau hinh (nhan dien - su dung)
@Configuration
@EnableWebSecurity
public class SecrurityConfiguration {

    /*Bean : danh dau day la 1 phuong thuc cua 1 lop Configuration
    * no se tao 1 phuong thuc -> duoc quan ly boi spring container
    * bean co the dc dependency inject vao cac bean khac trong ung dung
    * */
    @Bean

    //SecurityFilterChain : interface cua spring security
    /*SecurityFilterChain : Dung de cau hinh bo loc bao mat

    - HttpSecurity : cung cap nhieu phuong thuc de cau hinh bao mat

    - Khi ung dung khoi tao : spring se tao ra doi tuong SecurityFilterChain
    duoc quan ly boi spring container
    */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*HttpSecurity :  cung cap phuong thuc cau hinh bao mat*/
        return http

                /*.csrf(csrf -> csrf.disable()) : vo hieu hoa CSRF. CSRF ,
                day la 1 ky thuat tan cong
                CSRF : ki thuat chong tan cong
                CSRF : vo hieu hoa tan cong CSRF --> khi lam thuc te
                khong nen vo hieu hoa CSRF
                */

                /*Tắt CSRF protection*/
                .csrf(csrf -> csrf.disable())

                /*authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) :
                Cho phép mọi yêu cầu request đều được phép -> không có quy tắc
                phân quyền nào được áp dụng, mọi yêu cầu ều đc chấp nhận
                * */
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                /*authenticated : moi request phai duoc xac thuc truoc
                khi duoc bao mat
                * */

                .formLogin(Customizer.withDefaults())
                /*Build : ket thuc cau hinh bao mat va tra ve mot SecurityFilterChain
                * SecurityFilterChain : dai diện cho toàn bộ bộ lọc bảo mật đc cấu hình
                * */
                .build();
    }

    /*Ma hoa password*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /* Xác thực người dùng */
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService detailUserServices )
    {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        return new ProviderManager(daoProvider);
    }
}
