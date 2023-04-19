package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.DTO.VisitDTO;
import com.kosa.Catchvegan.Mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Override
    public RestaurantDTO restaurantmanage(int managerIdx) {
        return managerMapper.restaurantmanage(managerIdx);
    }

    @Override
    public void updaterestaurant(RestaurantDTO restaurantDTO) {
         managerMapper.updaterestaurant(restaurantDTO);
    }

    @Override
    public List<ReserveDTO> reservememberlist(ReserveDTO reserveDTO) {
        return managerMapper.reservememberlist(reserveDTO);
    }

    @Override
    public void confirmStatus(ReserveDTO reserveDTO) {
        managerMapper.confirmStatus(reserveDTO);
    }

    @Override
    public void createVisit(VisitDTO visitDTO) {
        managerMapper.createVisit(visitDTO);
    }


    //Manager Create
    @Override
    public List<ManagerDTO> findAllManagers() {
        return managerMapper.findAllManagers();
    }

    @Override
    public ManagerDTO createManager(ManagerDTO manager) {
        String pw = manager.getPassword();
        manager.setPassword(pe.encode(pw));
        managerMapper.createManager(manager);
        managerMapper.managerRole(manager.getManagerIdx());
        return null;
    }

    @Override
    public boolean managerIdGet(String id) {
        return managerMapper.managerIdGet(id) == null? false : true;
    }

    @Override
    public ManagerDTO managerGetUserByIdAndPassword(String id) {
        return managerMapper.managerGetUserByIdAndPassword(id);
    }

}
