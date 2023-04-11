package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberMapper {
    public List<MemberDTO> findAllMembers();
    public void createMember(MemberDTO member);
}
