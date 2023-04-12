package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public RestaurantDTO restaurantmanage(int managerIdx) {
        return managerMapper.restaurantmanage(managerIdx);
    }

    @Override
    public void updaterestaurant(RestaurantDTO restaurantDTO) {
         managerMapper.updaterestaurant(restaurantDTO);
    }

    @Override
    public List<ReserveDTO> reservememberlist(int restaurantIdx) {
        return managerMapper.reservememberlist(restaurantIdx);
    }

    @Override
    public void confirmstatus(ReserveDTO reserveDTO) {
        managerMapper.confirmstatus(reserveDTO);
    }


}
