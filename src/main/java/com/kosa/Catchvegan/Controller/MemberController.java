package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Security.JwtFilter;
import com.kosa.Catchvegan.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Catchvegan")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("")
    public String gomain(){
        return "main";
    }

    @PostMapping("/member/checkid")
    public ResponseEntity<String> checkId(@RequestBody MemberDTO memberDTO) {
        String id = memberDTO.getId();
        if(memberService.getId(id)){
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        return new ResponseEntity<>("fail",HttpStatus.OK);
    }

    @PostMapping("/member/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Secured("ROLE_USER")
    @PostMapping ("/member/aftersignup")
    public String aftersignup(){
        return "여기는 토큰있는사람만 올수있어";
    }

}