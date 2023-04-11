package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Mapper.ReserveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReserveServiceImpl implements ReserveService{

    @Autowired
    private ReserveMapper mapper;
    @Override
    public RestaurantDTO restaurantDetail(int restaurantIdx) {
        return mapper.restaurantDetail(restaurantIdx);
    }
}
