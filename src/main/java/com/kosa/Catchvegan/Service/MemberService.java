package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import org.apache.catalina.Manager;

import java.util.List;


public interface MemberService {

    //Member
    public List<MemberDTO> findAllMembers();

    public MemberDTO createMember(MemberDTO member);

    public boolean getId(String id);

    public MemberDTO getUserByIdAndPassword(String id);

    public void memberUpdate(MemberDTO memberDTO) throws Exception;

    public boolean findByPhone(String phone);
    public boolean idFindByPhone(String id);

    public void passwordUpdate(MemberDTO memberDTO) throws Exception;

    public String idFind(String phone);

    //아이디에 맞는 핸드폰 번호 반환
    public String phoneFind(String id);

    //DB에 아이디에 맞는 핸드폰 있는지 체크하기
    public boolean pwFindByIdCheck(String id);


}
