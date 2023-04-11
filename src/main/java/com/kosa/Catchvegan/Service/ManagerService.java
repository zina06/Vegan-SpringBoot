package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.RestaurantDTO;

public interface ManagerService  {
    public RestaurantDTO restaurantmanage(int managerIdx);
    public void updaterestaurant(RestaurantDTO restaurantDTO);
}
