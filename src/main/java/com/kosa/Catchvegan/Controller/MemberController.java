package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/member/checkid")
    public ResponseEntity<String> checkId(@RequestBody MemberDTO memberDTO) {
        String id = memberDTO.getId();
        if(memberService.getId(id)){
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        return new ResponseEntity<>("fail",HttpStatus.OK);
    }

    @PostMapping("/member/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        System.out.println("나는 멤버로 가입할꺼야");
        memberService.createMember(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/owner/signup")
    public ResponseEntity<String> adminsignup(@RequestBody ManagerDTO managerDTO){
        System.out.println("나는 어드민으로 가입할꺼야");
        memberService.createManager(managerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/member/aftersignup")
    public String aftersignup(){
        return "aftersignup";
    }

}