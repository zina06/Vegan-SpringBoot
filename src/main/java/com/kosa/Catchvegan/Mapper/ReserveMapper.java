package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReserveMapper {
    public RestaurantDTO restaurantDetail(int restaurantIdx);

    public List<ReserveDTO> reserveDate(ReserveDTO reserveDTO);
}
