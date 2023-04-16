package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.Service.MyDiningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Catchvegan")
@CrossOrigin(origins = "http://localhost:8081")
@Slf4j
public class MyDiningController {

    @Autowired
    private MyDiningService service;
    @GetMapping("/mydining/getReserves/{memberIdx}")
    public ResponseEntity<List<ReserveDTO>> getReserves(@PathVariable int memberIdx){
        MemberDTO dto = new MemberDTO();
        dto.setMemberIdx(memberIdx);
        return new ResponseEntity<>(service.getReserves(dto), HttpStatus.OK);
    }

    @GetMapping("/mydining/getVisitCompleted/{memberIdx}")
    public ResponseEntity<List<ReserveDTO>> getVisitCompleted(@PathVariable int memberIdx){
        MemberDTO dto = new MemberDTO();
        dto.setMemberIdx(memberIdx);
        return new ResponseEntity<>(service.getVisitCompleted(dto), HttpStatus.OK);
    }

    @GetMapping("/mydining/getReserveCancel/{memberIdx}")
    public ResponseEntity<List<ReserveDTO>> getReserveCancel(@PathVariable int memberIdx){
        MemberDTO dto = new MemberDTO();
        dto.setMemberIdx(memberIdx);
        return new ResponseEntity<>(service.getReserveCancel(dto), HttpStatus.OK);
    }
}
