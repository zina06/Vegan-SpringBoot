package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReserveMapper {
    public RestaurantDTO restaurantDetail(int restaurantIdx);

    public List<ReserveDTO> reserveDate(ReserveDTO reserveDTO);

    public void reserveRes(ReserveDTO reserveDTO);

    public void reservePay(PaymentDTO paymentDTO);

    public int  deleteReserve(ReserveDTO reserveDTO);
    public List<ReserveDTO> reserveDateAlert(ReserveDTO reserveDTO);

    public MemberDTO getMember(int memberIdx);

    public PaymentDTO getPayment(PaymentDTO paymentDTO);

    public int cancelReserve(int reserveIdx);

    public void cancelRes(CancelDTO dto);
    public int deletePayment(ReserveDTO reserveDTO);

    public List<ReserveDTO> canReserve(ReserveDTO reserveDTO);

    public List<ReserveDTO> noReserve();


}
