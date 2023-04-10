package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/member/join")
    public String saveMember(@RequestBody MemberDTO memberdto) throws Exception{
        return "success";
    }

}