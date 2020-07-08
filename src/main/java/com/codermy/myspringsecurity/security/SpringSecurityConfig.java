package com.codermy.myspringsecurity.security;

import com.codermy.myspringsecurity.security.auth.MyAuthenticationSuccessHandler;
import com.codermy.myspringsecurity.security.auth.MyLogoutSuccessHandle;
import com.codermy.myspringsecurity.security.auth.RestAuthenticationAccessDeniedHandler;
import com.codermy.myspringsecurity.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author codermy
 * @createTime 2020/7/2
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;
    @Autowired
    private MyLogoutSuccessHandle myLogoutSuccessHandle;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement();//关闭跨站
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers( "/login.html","/xadmin/**","/treetable-lay/**","/static/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
        .and().logout()
                .permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(myLogoutSuccessHandle);
        http.exceptionHandling().accessDeniedHandler(restAuthenticationAccessDeniedHandler);
    }
}
