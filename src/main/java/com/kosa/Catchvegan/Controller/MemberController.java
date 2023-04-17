package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Security.JwtFilter;
import com.kosa.Catchvegan.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
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

    @PreAuthorize("hasRole('ROLE_USER')")
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
    public ResponseEntity<String> authPhone(@RequestParam String phone){
        String userphone = String.valueOf(memberService.idFindByPhone(phone));
        if(userphone == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("핸드폰 번호를 찾을 수 없습니다.");
        return ResponseEntity.ok(userphone);
    }

    @ResponseBody
    @GetMapping("/member/findMyId")
    public ResponseEntity<String> findId(@RequestParam String id) {
        String userid = String.valueOf(memberService.idFindByPhone(id));
        if(userid == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디를 찾지 못했습니다.");
        return ResponseEntity.ok(userid);
    }

    @ResponseBody
    @PostMapping("/member/findMyPassword")
    public ResponseEntity<String> findPassword(@RequestBody MemberDTO memberDTO) {
        memberService.passwordUpdate(memberDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}