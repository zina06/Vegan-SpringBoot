package com.kosa.Catchvegan.Security;

import com.kosa.Catchvegan.DTO.ManagerAuthDTO;
import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberAuthDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Mapper.ManagerMapper;
import com.kosa.Catchvegan.Mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("================================" + username);
        MemberDTO member = memberMapper.getUserByIdAndPassword(username);
        ManagerDTO manager = managerMapper.managerGetUserByIdAndPassword(username);

        if (member != null){
            System.out.println("=================================================");
            System.out.println(member);
            System.out.println("=================================================");
            System.out.println(member.getRoles());
            String memberId = member.getId();
            String memberPw = member.getPassword();
//            List<MemberAuthDTO> memberRoles = member.getRoles();
            System.out.println(memberId);
            System.out.println(memberPw);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // member 객체에 ROLE_USER role 추가
            return User.builder()
                    .username(memberId)
                    .password(memberPw)
                    .authorities(authorities)
                    .build();
        } else if (manager != null){
            System.out.println("MANAGER");
            String managerId = manager.getId();
            String managerPw = manager.getPassword();
            List<ManagerAuthDTO> managerRoles = manager.getRoles();
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (managerRoles != null && !managerRoles.isEmpty()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER")); // manager 객체에 ROLE_MANAGER role 추가
            }
            return User.builder()
                    .username(managerId)
                    .password(managerPw)
                    .authorities(authorities)
                    .build();
        }   else {
            throw new UsernameNotFoundException("id : " + username + " is not found");
        }
    }
}
