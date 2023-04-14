package com.kosa.Catchvegan.Security;


import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Mapper.ManagerMapper;
import com.kosa.Catchvegan.Mapper.MemberMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class JwtFilter extends BasicAuthenticationFilter {

    private MemberMapper memberMapper;
    private ManagerMapper managerMapper;

    public JwtFilter(AuthenticationManager authenticationManager, MemberMapper memberMapper,
                     ManagerMapper managerMapper) {
        super(authenticationManager);
        this.memberMapper = memberMapper;
        this.managerMapper = managerMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request2, HttpServletResponse response2,
                                    FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) request2;
        HttpServletResponse response = (HttpServletResponse) response2;

        if (request.getHeader("AUTHORIZATION") == null) {
            onError(response, "UNAUTHORIZATION");
            return;
        } else {
            String authorizationHeader = request.getHeader("AUTHORIZATION");
            System.out.println("AUTHORIZATION : " + authorizationHeader);
            String jwt = authorizationHeader.replace("Bearer", "");
            if (!isJwtValid(jwt)) {
                onError(response, "UNAUTHORIZATION");
                return;
            }
            String name = Jwts.parser().setSigningKey("hello").parseClaimsJws(jwt).getBody().getSubject();
            MemberDTO memberDTO = memberMapper.getUserByIdAndPassword(name);
            ManagerDTO managerDTO = managerMapper.managerGetUserByIdAndPassword(name);
            if (memberDTO != null) {
                UserDetails user = User.builder()
                        .username(memberDTO.getId())
                        .password(memberDTO.getPassword())
                        .authorities(memberDTO.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                                .collect(Collectors.toList()))
                        .build();
//                Authentication authentication = new UsernamePasswordAuthenticationToken(
//                        user,
//                        memberDTO.getPassword(),
//                        memberDTO.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
//                                .collect(Collectors.toList()));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated() && authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))) {
                    System.out.println("통과통과통괕와토나ㅓ람넉우하ㅣㅓㅁ우하ㅓㅁㅋㅇ궇");
                    filterChain.doFilter(request, response);
                } else {
                    // 권한이 없으면 거부 처리 또는 다른 처리를 수행
                    // 예를 들어, 응답 상태 코드를 변경하거나 예외를 던질 수 있음
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
                filterChain.doFilter(request2, response2);
            } else if (managerDTO != null) {
                UserDetails user = User.builder()
                        .username(managerDTO.getId())
                        .password(managerDTO.getPassword())
                        .authorities(managerDTO.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                                .collect(Collectors.toList()))
                        .build();
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        managerDTO.getPassword(),
                        managerDTO.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                                .collect(Collectors.toList()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request2, response2);
            } else {
                throw new UsernameNotFoundException("유저없음");
            }
        }
    }

        public boolean isJwtValid(String jwt) {
            boolean returnValue = true;
            String subject = null;
            try {
                subject = Jwts.parser().setSigningKey("hello").parseClaimsJws(jwt).getBody().getSubject();
                System.out.println("subject : " + subject);
            }catch (Exception e){
                returnValue=false;
            }
            if(subject==null || subject.isEmpty()){
                returnValue = false;
            }
            return returnValue;
        }

    private void onError(HttpServletResponse response, String httpStatus) throws IOException{
        response.addHeader("error", httpStatus);
        response.sendError(HttpServletResponse.SC_FORBIDDEN,httpStatus);
    }
}

