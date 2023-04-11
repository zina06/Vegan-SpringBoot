package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Catchvegan")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/member/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        MemberDTO dto = memberService.createMember(memberDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}