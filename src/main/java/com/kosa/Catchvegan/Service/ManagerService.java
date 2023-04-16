package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;

import java.util.List;

public interface ManagerService  {
    public RestaurantDTO restaurantmanage(int managerIdx);
    public void updaterestaurant(RestaurantDTO restaurantDTO);
    public List<ReserveDTO> reservememberlist(ReserveDTO reserveDTO);
    public void confirmstatus(ReserveDTO reserveDTO);


    //Manager create
    public List<ManagerDTO> findAllManagers();

    public ManagerDTO createManager(ManagerDTO manager);

    public boolean managerIdGet(String id);

    public ManagerDTO managerGetUserByIdAndPassword(String id);
}
