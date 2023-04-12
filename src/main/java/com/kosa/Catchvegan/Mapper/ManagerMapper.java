package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {
    public RestaurantDTO restaurantmanage(int managerIdx);
    public void updaterestaurant(RestaurantDTO restaurantDTO);
    public List<ReserveDTO> reservememberlist(int restaurantIdx);
    public void confirmstatus(ReserveDTO reserveDTO);
}
