package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;

import java.util.List;

public interface ManagerService  {
    public RestaurantDTO restaurantmanage(int managerIdx);
    public void updaterestaurant(RestaurantDTO restaurantDTO);
    public List<ReserveDTO> reservememberlist(int restaurantIdx);
    public void confirmstatus(ReserveDTO reserveDTO);
}
