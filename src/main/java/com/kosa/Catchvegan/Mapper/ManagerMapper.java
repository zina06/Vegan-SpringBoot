package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.DTO.VisitDTO;
import org.apache.ibatis.annotations.Mapper;
import com.kosa.Catchvegan.DTO.ManagerDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerMapper {
    public RestaurantDTO getOneRestaurant(int managerIdx);

    public void updateRestaurant(RestaurantDTO restaurantDTO);

    public List<ReserveDTO> reserveMemberList(ReserveDTO reserveDTO);
    public void confirmStatus(ReserveDTO reserveDTO);

    public void createVisit(VisitDTO visitDTO);

    public List<ManagerDTO> findAllManagers();

    public Long createManager(ManagerDTO manager);

    public void managerRole(@Param("managerIdx") Integer managerIdx);

    public ManagerDTO managerGetUserByIdAndPassword(@Param("id") String id);

    public ManagerDTO managerIdGet(@Param("id") String id);

    public List<ReserveDTO> findAllReserve(RestaurantDTO restaurantDTO);


}
