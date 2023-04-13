package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.PaymentDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.DTO.TimeDTO;

import java.util.List;

public interface ReserveService {
    public RestaurantDTO restaurantDetail(int restaurantIdx);

    public TimeDTO reserveDate(ReserveDTO reserveDTO);

    public Long reserveRes(ReserveDTO reserveDTO);

    public void reservePay(PaymentDTO paymentDTO);

    public int deleteReserve(ReserveDTO reserveDTO);
}
