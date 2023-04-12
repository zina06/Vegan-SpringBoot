package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.RestaurantDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReserveMapper {
    public RestaurantDTO restaurantDetail(int restaurantIdx);
}
