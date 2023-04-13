package com.kosa.Catchvegan.mapper;


import com.kosa.Catchvegan.DTO.PaymentDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.Mapper.ReserveMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReserveMapperTest {

    @Autowired
    private ReserveMapper mapper;
//    @Test
//    public void test1(){
//        ReserveDTO reserveDTO = new ReserveDTO();
//        reserveDTO.setReserveDate(new Date());
//        reserveDTO.setRestaurantIdx(5);
//        System.out.println(mapper.reserveDate(reserveDTO));
//    }

//    @Test
//    public void test1(){
//        ReserveDTO reserveDTO = new ReserveDTO();
//        reserveDTO.setReserveDate(new Date());
//        reserveDTO.setRestaurantIdx(5);
//        reserveDTO.setMemberIdx(1);
//        reserveDTO.setResCount(4);
//        mapper.reserveRes(reserveDTO);
//    }
    @Test
    public void test1(){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setTid("123");
        paymentDTO.setReserveIdx(9);
        paymentDTO.setPayAmount(1000);
        paymentDTO.setPayInfo("");
        mapper.reservePay(paymentDTO);
    }
}
