package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyDiningMapper {

    public List<ReserveDTO> getReserves(MemberDTO dto);

    public List<ReserveDTO> getVisitCompleted(MemberDTO dto);

    public List<ReserveDTO> getReserveCancel(MemberDTO dto);

    public List<ReserveDTO> goReviewButton(MemberDTO dto);
}
