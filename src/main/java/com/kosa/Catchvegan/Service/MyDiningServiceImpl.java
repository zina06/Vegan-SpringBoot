package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.Mapper.MyDiningMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyDiningServiceImpl implements MyDiningService{

    @Autowired
    private MyDiningMapper mapper;
    @Override
    public List<ReserveDTO> getReserves(MemberDTO dto) {
        return mapper.getReserves(dto);
    }

    @Override
    public List<ReserveDTO> getReserveCancel(MemberDTO dto) {
        return mapper.getReserveCancel(dto);
    }

    @Override
    public List<ReserveDTO> goReviewButton(MemberDTO dto) {
        return mapper.goReviewButton(dto);
    }


}
