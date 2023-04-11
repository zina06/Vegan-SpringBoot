package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberMapper memberMapper;

    public List<MemberDTO> findAllMembers(){
        return memberMapper.findAllMembers();
    }

    public void createMember(MemberDTO member){
        memberMapper.createMember(member);
    }
}
