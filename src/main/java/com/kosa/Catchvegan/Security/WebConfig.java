package com.kosa.Catchvegan.Security;

import com.kosa.Catchvegan.Mapper.ManagerMapper;
import com.kosa.Catchvegan.Mapper.MemberMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.request.WebRequest;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    private MemberMapper memberMapper;
    private ManagerMapper managerMapper;

    private CustomUserDetailService customUserDetailService;

    public WebConfig(MemberMapper memberMapper, ManagerMapper managerMapper,
            CustomUserDetailService customUserDetailService) {
        this.memberMapper = memberMapper;
        this.managerMapper = managerMapper;
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/Catchvegan")
                .antMatchers("/Catchvegan/member/checkid")
                .antMatchers("/Catchvegan/member/signup");
        // 이 요청들에 대해서는 spring security 필터 체인을 적용하지 않겠다
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/Catchvegan").permitAll()
                .antMatchers("/Catchvegan/member/checkid").permitAll()
                .antMatchers("/Catchvegan/member/signup").permitAll()
                .and()
                .addFilter(authenticationFilter())
                .addFilter(JwtFilter()).authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }

    private JwtFilter JwtFilter() throws Exception {
        return new JwtFilter(authenticationManager(), memberMapper, managerMapper);
    }

    private AuthenticationFilter authenticationFilter() throws Exception {
        return new AuthenticationFilter(authenticationManager(), managerMapper, memberMapper);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    /*
     * 시큐리티 설정 제거
     * 
     * @Bean
     * public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
     * Exception {
     * return http.authorizeRequests().antMatchers("/").permitAll().and().build();
     * }
     */

}
