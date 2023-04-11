package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.RestaurantDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerMapper {
    public RestaurantDTO restaurantmanage(int managerIdx);
    public void updaterestaurant(RestaurantDTO restaurantDTO);
}
