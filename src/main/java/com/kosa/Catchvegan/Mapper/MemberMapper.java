package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    public List<MemberDTO> findAllMembers();
    public Long createMember(MemberDTO member);

    public void userRole(@Param("memberIdx") Integer memberIdx);

    public MemberDTO getUserByIdAndPassword(@Param("id") String id);
    public MemberDTO idGet(@Param("id") String id);

    public void memberUpdate(MemberDTO memberDTO);

    public void memberRemove(MemberDTO memberDTO);

    public  MemberDTO findByPhone(String phone);
    public  MemberDTO idFindByPhone(String id);

    //아이디에 맞는 핸드폰 번호 반환
    public  MemberDTO pwFindById(String id);

    //DB에 아이디에 맞는 핸드폰 있는지 체크하기
    public  MemberDTO pwFindByIdCheck(String id);

    public void passwordUpdate(MemberDTO memberDTO);

}
