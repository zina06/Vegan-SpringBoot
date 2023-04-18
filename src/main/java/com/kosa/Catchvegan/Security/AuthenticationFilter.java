package com.kosa.Catchvegan.Security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Mapper.ManagerMapper;
import com.kosa.Catchvegan.Mapper.MemberMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ManagerMapper managerMapper;
    private MemberMapper memberMapper;

    //로그인 처리
    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                ManagerMapper managerMapper,MemberMapper memberMapper){
        super.setAuthenticationManager(authenticationManager);
        this.managerMapper = managerMapper;
        this.memberMapper = memberMapper;
        setFilterProcessesUrl("/Catchvegan/member/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("=========== attemptAuthentication 접근 ===========");
        try {
            MemberDTO md = new ObjectMapper().readValue(request.getInputStream(),
                    MemberDTO.class);
            MemberDTO mds = memberMapper.getUserByIdAndPassword(md.getId());
            ManagerDTO mg = new ManagerDTO();
            mg.setId(md.getId());
            mg.setPassword(md.getPassword());
            ManagerDTO mas = managerMapper.managerGetUserByIdAndPassword(mg.getId());
            if (mas != null) {
                System.out.println("=========== attemptAuthentication 매니저 ================");
                return getAuthenticationManager().authenticate(
                        new UsernamePasswordAuthenticationToken(
                                mas.getId(),
                                mg.getPassword(),
                                new ArrayList<>())
                );
            } else if (mds != null) {
                System.out.println("=========== attemptAuthentication 맴버 ================");
                return getAuthenticationManager().authenticate(
                        new UsernamePasswordAuthenticationToken(
                                mds.getId(),
                                md.getPassword(),
                                new ArrayList<>())
                );
            } else {
                throw new UsernameNotFoundException("아이디 없음");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String userName = ((User)authResult.getPrincipal()).getUsername();
        MemberDTO memberDTO = memberMapper.getUserByIdAndPassword(userName);
        String jwt = Jwts.builder()
                .setHeaderParam("type", "jwt")
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
                .signWith(SignatureAlgorithm.HS256, "hello")
                .compact();
        response.addHeader("token",jwt);
        response.addHeader("memberIdx", memberDTO.getMemberIdx().toString());
    }
}
