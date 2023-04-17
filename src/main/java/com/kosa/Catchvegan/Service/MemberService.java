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

    public void memberUpdate(MemberDTO memberDTO);

    public boolean findByPhone(String phone);
    public boolean idFindByPhone(String id);

    public void passwordUpdate(MemberDTO memberDTO);


}
