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

    //@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping ("/member/aftersignup")
    public String aftersignup(Principal principal){
        System.out.println("principal2 = " + principal);
        System.out.println(principal.getName());
        return "여기는 멤버 토큰있는사람만 올수있어";
    }

    @PutMapping("/member/mypage")
    public ResponseEntity<MemberDTO> memberModify(@RequestBody MemberDTO memberDTO){
        memberService.memberUpdate(memberDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/authPhone/{phone}")
    public ResponseEntity<Map<String, String>> authPhone(@PathVariable String phone) {
        if (!memberService.findByPhone(phone)) {
            System.out.println(phone);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", "휴대폰 번호를 찾지 못했습니다."));
        }
        String authNo = signUp.sendSMS("+82" + phone);
        Map<String, String> response = new HashMap<>();
        System.out.println("phonephonephonephonephonephonephonephonephonephone"+phone);
        System.out.println("authNoauthNoauthNoauthNoauthNoauthNoauthNoauthNo"+authNo);
        response.put("phone", phone);
        response.put("authNo", authNo);
        return ResponseEntity.ok(response);
    }


    /*
    @ResponseBody
    @GetMapping("/member/findMyId")
    public ResponseEntity<String> findId(@RequestParam String id) {
        tring authNo = authPhone().getBody().get("authNo");
        String authNoCheck = String.valueOf(authNo);
        if(authNo.equals(authNoCheck)) { // 인증번호와 입력한 텍스트가 같으면
            return new ResponseEntity<>("일치", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("불일치", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(id);
    }
    */


    @ResponseBody
    @PostMapping("/member/findMyPassword")
    public ResponseEntity<String> findPassword(@RequestBody MemberDTO memberDTO) {
        memberService.passwordUpdate(memberDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}