package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Quartz.SignUpSMS;
import com.kosa.Catchvegan.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Session;
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
        this.authPhone(memberDTO.getPhone());
        memberService.createMember(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping ("/member/aftersignup")
    public String aftersignup(Principal principal){
        System.out.println("principal2 = " + principal);
        System.out.println(principal.getName());
        return "여기는 멤버 토큰있는사람만 올수있어";
    }

    @PutMapping("/member/mypage")
    public ResponseEntity<MemberDTO> memberModify(@RequestBody MemberDTO memberDTO) {

        try{
            memberService.memberUpdate(memberDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    // 회원 가입 할때만 인증번호만 보내는 컨트롤러
    @GetMapping("/authPhone/signup/{phone}")
    public ResponseEntity<Map<String, String>> authPhone(@PathVariable String phone) {
        if (!memberService.findByPhone(phone)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", "휴대폰 번호를 찾지 못했습니다."));
        }
        String authNo = signUp.sendSingupSMS("+82" + phone);
        Map<String, String> response = new HashMap<>();
        response.put("phone", phone);
        response.put("authNo", authNo);
        System.out.println("phonephonephonephonephonephonephonephonephonephone"+phone);
        System.out.println("authNoauthNoauthNoauthNoauthNoauthNoauthNoauthNo"+authNo);
        return ResponseEntity.ok(response);
    }

    // ID찾을때만 인증번호만 보내는 컨트롤러
    @GetMapping("/authPhone/findMyId/{phone}")
    public ResponseEntity<String> findMyIdauthPhone(@PathVariable String phone) {
        if (!memberService.findByPhone(phone)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("error");
        }
        int authNum = signUp.sendIdSMS("+82" + phone);
        String authNo = String.valueOf(authNum);
        System.out.println("authNoauthNoauthNoauthNoauthNoauthNoauthNoauthNo"+authNo);
//        return ResponseEntity.ok(authNo);
        return new ResponseEntity<>(authNo, HttpStatus.OK);
    }

    // ID 반환
    @ResponseBody
    @GetMapping("/authPhone/idget/{phone}")
    public ResponseEntity<String> findId(@PathVariable String phone) {
        String phone2= "+82"+phone;
        String id = memberService.idFind(phone2);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }



    @ResponseBody
    @PostMapping("/member/findMyPassword")
    public ResponseEntity<String> findPassword(@RequestBody MemberDTO memberDTO) {
        memberService.passwordUpdate(memberDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }



        /*
    @GetMapping("/member/findMyId")
    public ResponseEntity<Map<String, String>> findId(@RequestParam String id) {
        String authNo = signUp.sendSingupSMS("+82" + phone);
        Map<String, String> response = new HashMap<>();
        response.put("phone", phone);
        response.put("authNo", authNo);
        System.out.println("phonephonephonephonephonephonephonephonephonephone"+phone);
        System.out.println("authNoauthNoauthNoauthNoauthNoauthNoauthNoauthNo"+authNo);
        return ResponseEntity.ok(response);
    }
     */


}