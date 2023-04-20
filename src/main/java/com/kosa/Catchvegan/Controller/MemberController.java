package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Quartz.SignUpSMS;
import com.kosa.Catchvegan.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/Catchvegan")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private SignUpSMS signUp;

    @GetMapping("")
    public String goMain(){
        return "main";
    }

    @PostMapping ("/member/aftermember")
    public String afterMember(){
        return "여기는 멤버 토큰있는사람만 올수있어";
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

    @PutMapping("/member/mypage")
    public ResponseEntity<MemberDTO> memberModify(@RequestBody MemberDTO memberDTO) {

        try{
            memberService.memberUpdate(memberDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 가입 할때만 인증번호만 보내는 컨트롤러
    @GetMapping("/authPhone/signup/{phone}")
    public ResponseEntity<String> authPhone (@PathVariable String phone){
        int authNum = signUp.sendSingUpSMS("+82" + phone);
        String authNo = String.valueOf(authNum);
        return new ResponseEntity<>(authNo, HttpStatus.OK);
    }

    // ID찾을때만 인증번호만 보내는 컨트롤러
    @GetMapping("/authPhone/findMyId/{phone}")
    public ResponseEntity<String> findMyIdAuthPhone (@PathVariable String phone){
        if (!memberService.findByPhone(phone)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error");
        }
        int authNum = signUp.sendIdSMS("+82" + phone);
        String authNo = String.valueOf(authNum);
        return new ResponseEntity<>(authNo, HttpStatus.OK);
    }

    // ID 반환
    @GetMapping("/authPhone/idGet/{phone}")
    public ResponseEntity<String> findId (@PathVariable String phone){
        String phone2 = "+82" + phone;
        String id = memberService.idFind(phone2);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    // PW찾을때만 인증번호만 보내는 컨트롤러
    @GetMapping("/authPhone/findMyPassword/{id}")
    public ResponseEntity<String> findMyPwAuthPhone (@PathVariable String id){
        try{
            String phone = memberService.phoneFind(id);
            int authNum = signUp.sendIdSMS(phone);
            String authNo = String.valueOf(authNum);
            return new ResponseEntity<>(authNo, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("error" ,HttpStatus.CONFLICT);
        }

    }

    // PW 변경
    @PutMapping("/authPhone/pwGet/{id}")
    public ResponseEntity<String> findPassword (@RequestBody MemberDTO memberDTO){
        try{
            memberService.passwordUpdate(memberDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //멤버탈퇴
    @PutMapping("/member/myPage/remove")
    public ResponseEntity<MemberDTO> memberRemove(@RequestBody MemberDTO memberDTO) {
        try{
            memberService.memberRemove(memberDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}