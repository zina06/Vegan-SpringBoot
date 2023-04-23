package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.DTO.CancelDTO;
import com.kosa.Catchvegan.DTO.PaymentDTO;
import com.kosa.Catchvegan.DTO.RefundDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.Service.ReserveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReserveServiceImplTest {

    @Autowired
    private ReserveService service;
    @Test
    void restaurantDetail() {
        service.restaurantDetail(1);
    }

    @Test
    void reserveRes() {
        ReserveDTO reserveDTO = new ReserveDTO();
        Date nowDate = new Date();
        reserveDTO.setMemberIdx(1);
        reserveDTO.setRestaurantIdx(1);
        reserveDTO.setReserveDate(nowDate);
        reserveDTO.setResCount(2);

        service.reserveRes(reserveDTO);
    }

    @Test
    void reservePay() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setReserveIdx(1);
        paymentDTO.setPayAmount(3000);
        paymentDTO.setPayInfo("테스트");
        paymentDTO.setTid("테스트 코드");

        service.reservePay(paymentDTO);
    }

    @Test
    void deleteReserve() {
        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setReserveIdx(3L);

        service.deleteReserve(reserveDTO);
    }

    @Test
    void getMember() {
        service.getMember(1);
    }

    @Test
    void getPayment() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setReserveIdx(1);
    }

    @Test
    void cancelReserve() {
        service.cancelReserve(1);
    }

    @Test
    void cancelRes() {
        CancelDTO cancelDTO = new CancelDTO();
        cancelDTO.setReserveIdx(1);
        service.cancelRes(cancelDTO);
    }

    @Test
    void deletePayment() {
        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setReserveIdx(1L);
        service.deletePayment(reserveDTO);
    }

    @Test
    void canReserve() {
        ReserveDTO reserveDTO = new ReserveDTO();
        Date nowDate = new Date();

        reserveDTO.setMemberIdx(1);
        reserveDTO.setReserveDate(nowDate);
    }

    @Test
    void noReserve() {
        service.noReserve();
    }
    @Test
    void noReserve2() {
        service.noReserve();
    }

    @Test
    void createRefund() {
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setPayIdx(1);
        refundDTO.setRefundAmount(2000);

        service.createRefund(refundDTO);
    }
}