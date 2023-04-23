package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Service.MyDiningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MyDiningServiceImplTest {

    @Autowired
    private MyDiningService service;
    @Test
    void getReserves() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberIdx(1);
        service.getReserves(memberDTO);
    }

    @Test
    void getReserveCancel() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberIdx(1);
        service.getReserveCancel(memberDTO);
    }

    @Test
    void goReviewButton() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberIdx(1);
        service.goReviewButton(memberDTO);
    }
}