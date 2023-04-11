package com.kosa.Catchvegan.Controller;


import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String main(){
        return "success";
    }

    @PostMapping("/member/join")
    public String saveMember(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
        return "success";
    }

}