package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.DTO.VisitDTO;
import com.kosa.Catchvegan.Service.ManagerService;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManagerServiceImplTest {

    @Autowired
    private ManagerService service;

    @Autowired
    private BCryptPasswordEncoder pe;
    @Test
    void getOneRestaurant() {
        service.getOneRestaurant(1);
    }

    @Test
    void updateRestaurant() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName("테스트 레스토랑");
        restaurantDTO.setRestaurantInfo("테스트 정보");
        restaurantDTO.setImages("테스트 사진");
        restaurantDTO.setMenu("테스트 메뉴");
        restaurantDTO.setRestaurantIdx(3);
        service.updateRestaurant(restaurantDTO);
    }

    @Test
    void reserveMemberList() {
        ReserveDTO reserveDTO = new ReserveDTO();

        Date nowDate = new Date();

        reserveDTO.setRestaurantIdx(3);
        reserveDTO.setReserveDate(nowDate);
        service.reserveMemberList(reserveDTO);
    }

    @Test
    void confirmStatus() {
        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setVisitStatus("o");
        reserveDTO.setReserveIdx(3L);

        service.confirmStatus(reserveDTO);
    }

    @Test
    void createVisit() {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setReserveIdx(3);

        service.createVisit(visitDTO);
    }

    @Test
    void findAllManagers() {
        service.findAllManagers();
    }

    @Test
    void createManager() {
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setRestaurantIdx(1);
        managerDTO.setId("테스트 아이디2");
        managerDTO.setPassword("테스트 비밀번호");

        service.createManager(managerDTO);
    }

    @Test
    void managerIdGet() {
        service.managerIdGet("admin");
    }

    @Test
    void managerGetUserByIdAndPassword() {
        service.managerGetUserByIdAndPassword("admin");
    }

    @Test
    void findAllReserve() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestaurantIdx(1);
        service.findAllReserve(restaurantDTO);
    }
}