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
                .antMatchers("/Catchvegan/error")
                .antMatchers("/Catchvegan/authPhone/**")
                .antMatchers("/Catchvegan/restaurant/**")
                .antMatchers("/Catchvegan/oneMember/**")
                .antMatchers("/Catchvegan/member/findMyId")
                .antMatchers("/Catchvegan/member/findMyPassword")
                .antMatchers("/Catchvegan/member/signup")
                .antMatchers("/Catchvegan/member/signup/**")
                .antMatchers("/Catchvegan/member/checkid")
                .antMatchers("/Catchvegan/manager/signup")
                .antMatchers("/Catchvegan/reserve-result") // 카카오페이
                .antMatchers("/Catchvegan/reserve/success/**") // 카카오페이
                .antMatchers("/Catchvegan/reserve/cancel/**") // 카카오페이
                .antMatchers("/Catchvegan/reserve/fail/**") // 카카오페이
                .antMatchers("/Catchvegan/restaurant/get/**") // 식당상세정보
                .antMatchers("/Catchvegan/search/**")
                .antMatchers("/Catchvegan/review/recent");
        // 이 요청들에 대해서는 spring security 필터 체인을 적용하지 않겠다
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/Catchvegan/manager/findMyPassword").permitAll()
                .antMatchers("/Catchvegan/manager/signup/**").permitAll()
                .antMatchers("/Catchvegan/restaurant/**").permitAll()
                .antMatchers("/Catchvegan/member/aftersignup").access("hasRole('ROLE_USER')")
                .antMatchers("/Catchvegan/reserve/**").access("hasRole('ROLE_USER')") // 예약페이지는 유저만 접근가능
                .antMatchers("/Catchvegan/mydining/**").access("hasRole('ROLE_USER')") // My-dining 페이지는 유저만 접근가능
                .antMatchers("/Catchvegan/member/mypage/**").access("hasRole('ROLE_USER')") // 마이페이지는 유저만 접근가능
                .antMatchers("/Catchvegan/manager/**").access("hasRole('ROLE_MANAGER')") // 매니저페이지는 매니저만
                .antMatchers("/file/**").access("hasRole('ROLE_MANAGER')") // 식당정보수정도 매니저만
                .antMatchers("/files/**").access("hasRole('ROLE_USER')")
                .anyRequest().authenticated() // authenticated()는 가장 마지막에 위치하도록 변경
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .addFilter(authenticationFilter())
                .addFilter(JwtFilter())
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

    @Bean
    public CustomAccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
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
