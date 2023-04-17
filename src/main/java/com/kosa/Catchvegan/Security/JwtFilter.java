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
        System.out.println("doFilterInternaldoFilter 접속");
        System.out.println(request.getHeader("AUTHORIZATION"));
        if (request.getHeader("AUTHORIZATION") == null) {
            System.out.println("AUTHORIZATION 로그인 안한 사람");
            onError(response, "UNAUTHORIZATION");
            return;
        } else {
            String authorizationHeader = request.getHeader("AUTHORIZATION");
            System.out.println("AUTHORIZATION : " + authorizationHeader);
            String jwt = authorizationHeader.replace("Bearer", "");
            if (!isJwtValid(jwt)) {
                System.out.println("!isJwtValid 토큰 없는거 같은데?");
                onError(response, "UNAUTHORIZATION");
                return;
            }
            String name = Jwts.parser().setSigningKey("hello").parseClaimsJws(jwt).getBody().getSubject();
            MemberDTO memberDTO = memberMapper.getUserByIdAndPassword(name);
            ManagerDTO managerDTO = managerMapper.managerGetUserByIdAndPassword(name);
            if (memberDTO != null) {
                System.out.println("나는 멤버야나는 멤버야나는 멤버야나는 멤버야나는 멤버야나는 멤버야나는 멤버야나는 멤버야");
                UserDetails user = User.builder()
                        .username(memberDTO.getId())
                        .password(memberDTO.getPassword())
                        .authorities(memberDTO.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                                .collect(Collectors.toList()))
                        .build();
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                       user,
                       memberDTO.getPassword(),
                       memberDTO.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                              .collect(Collectors.toList()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("authenticationauthenticationauthenticationauthentication" + authentication);
                if (authentication != null && authentication.isAuthenticated() && authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))) {
                    filterChain.doFilter(request, response);
                }
                else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
//                filterChain.doFilter(request2, response2);
            } else if (managerDTO != null) {
                System.out.println("나는어드민이야나는어드민이야나는어드민이야나는어드민이야나는어드민이야나는어드민이야나는어드민이야");
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
                System.out.println("authenticationauthenticationauthenticationauthentication" + SecurityContextHolder.getContext().getAuthentication());
                if (authentication != null && authentication.isAuthenticated() && authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_MANAGER"))) {
                    filterChain.doFilter(request, response);
                }
                else {
//                    System.out.println("430430430430430430430430430403403403403403403403403403");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
//                filterChain.doFilter(request2, response2);
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

