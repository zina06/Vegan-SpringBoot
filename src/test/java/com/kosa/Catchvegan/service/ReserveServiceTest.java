package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.Mapper.ReserveMapper;
import com.kosa.Catchvegan.Service.ReserveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReserveServiceTest {

    @Autowired
    private ReserveService service;
//    @Test
//    public void test1(){
//        ReserveDTO reserveDTO = new ReserveDTO();
//        reserveDTO.setReserveDate(new Date());
//        reserveDTO.setRestaurantIdx(5);
//        System.out.println(service.reserveDate(reserveDTO));
//    }
    @Test
    public void test1(){
        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setMemberIdx(1);
        reserveDTO.setResCount(13);
        reserveDTO.setReserveDate(new Date());
        reserveDTO.setRestaurantIdx(5);
        System.out.println(service.reserveRes(reserveDTO));
    }
}
