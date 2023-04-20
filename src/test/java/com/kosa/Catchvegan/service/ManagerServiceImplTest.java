package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.Mapper.ManagerMapper;
import com.kosa.Catchvegan.Service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManagerServiceImplTest {

    @Autowired
    private ManagerService service;

    @Test
    void restaurantmanage() {
        System.out.println(service.restaurantmanage(2));

    }

    @Test
    void updaterestaurant() {
    }

    @Test
    void reservememberlist() {

    }

    @Test
    void confirmStatus() {
    }

    @Test
    void createVisit() {
    }

    @Test
    void findAllManagers() {
        service.findAllManagers();
    }

    @Test
    void createManager() {
    }

    @Test
    void managerIdGet() {
        service.managerIdGet("111");
    }

    @Test
    void managerGetUserByIdAndPassword() {
    }
}