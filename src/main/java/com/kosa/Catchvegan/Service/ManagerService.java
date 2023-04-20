package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.*;

import java.util.List;

public interface ManagerService  {
    public RestaurantDTO getOneRestaurant(int managerIdx);
    public void updateRestaurant(RestaurantDTO restaurantDTO);
    public List<ReserveDTO> reserveMemberList(ReserveDTO reserveDTO);
    public void confirmStatus(ReserveDTO reserveDTO);
    public void createVisit(VisitDTO visitDTO);

    //Manager create
    public List<ManagerDTO> findAllManagers();

    public ManagerDTO createManager(ManagerDTO manager);

    public boolean managerIdGet(String id);

    public ManagerDTO managerGetUserByIdAndPassword(String id);

    public List<ReserveDTO> findAllReserve (RestaurantDTO restaurantDTO);
}
