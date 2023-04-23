package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService service;
    @Autowired
    private PasswordEncoder pw;
    @Test
    void findAllMembers() {
        service.findAllMembers();
    }

    @Test
    @Transactional
    void createMember() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId("테스트아이디");
        memberDTO.setPassword("테스트 비밀번호");
        memberDTO.setName("테스트 이름");
        memberDTO.setPhone("테스트 번호");
        memberDTO.setEmail("테스트 이메일");
        memberDTO.setVeganType("테스트 비건 타입");

        service.createMember(memberDTO);
    }

    @Test
    void getId() {
        service.getId("hanec");
    }
    @Test
    void getId2() {
        service.getId("hanec");
    }

    @Test
    void getUserByIdAndPassword() {
        service.getUserByIdAndPassword("테스트 아이디");
    }

    @Test
    void memberUpdate() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId("hanec");
        memberDTO.setMemberIdx(1);
        memberDTO.setPassword("테스트 수정 비번1");
        memberDTO.setPhone("테스트 수정 번호");
        memberDTO.setEmail("테스트 수정 이메일");
        memberDTO.setVeganType("테스트 수정 비건타입");
        service.memberUpdate(memberDTO);
    }

    @Test
    void memberRemove() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId("hanec");
        memberDTO.setPassword("테스트 수정 비번4");
        service.memberRemove(memberDTO);
    }

    @Test
    void findByPhone() {
        service.findByPhone("테스트 수정 번호");
    }
    @Test
    void findByPhone2() {
        service.findByPhone("12312314214");
    }

    @Test
    void idFindByPhone() {
        service.idFindByPhone("hanec");
    }
    @Test
    void idFindByPhone2() {
        service.idFindByPhone("12321414");
    }

    @Test
    void idFind() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setPhone("테스트 수정 번호");
        service.idFind("테스트 수정 번호");
    }

    @Test
    void phoneFind() {
        service.phoneFind("hanec");
    }

    @Test
    void pwFindByIdCheck() {
        service.pwFindByIdCheck("테스트 아이디");
        service.pwFindByIdCheck("hanec");
    }

    @Test
    void passwordUpdate() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId("hanec");
        memberDTO.setMemberIdx(1);
        memberDTO.setPassword("테스트 수정 비번2");
        memberDTO.setPhone("테스트 수정 번호");
        memberDTO.setEmail("테스트 수정 이메일");
        memberDTO.setVeganType("테스트 수정 비건타입");
        service.passwordUpdate(memberDTO);
    }
}