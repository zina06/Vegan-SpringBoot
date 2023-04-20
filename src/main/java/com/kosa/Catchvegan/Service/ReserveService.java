package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReserveService {
    public RestaurantDTO restaurantDetail(int restaurantIdx);

    public TimeDTO reserveDate(ReserveDTO reserveDTO);

    public Long reserveRes(ReserveDTO reserveDTO);

    public void reservePay(PaymentDTO paymentDTO);

    public int deleteReserve(ReserveDTO reserveDTO);

    public MemberDTO getMember(int memberIdx);

    public PaymentDTO getPayment(PaymentDTO paymentDTO);

    public int cancelReserve(int reserveIdx);

    public void cancelRes(CancelDTO dto);
    public int deletePayment(ReserveDTO reserveDTO);

    public boolean canReserve (ReserveDTO reserveDTO);

    public void  noReserve();
    public void createRefund(RefundDTO refundDTO);
}
