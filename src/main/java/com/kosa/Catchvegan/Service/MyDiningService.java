package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;

import java.util.List;

public interface MyDiningService {
    public List<ReserveDTO> getReserves(MemberDTO dto);
}
