package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MemberService {

    public List<MemberDTO> findAllMembers();

    public void createMember(MemberDTO member);

}
