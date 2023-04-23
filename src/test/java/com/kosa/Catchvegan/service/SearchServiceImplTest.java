package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.DTO.Criteria;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SearchServiceImplTest {

    @Autowired
    private SearchService service;

    @Test
    void restaurantAllShow() {
        service.restaurantAllShow();
    }

    @Test
    void getAddressWithPagingList() {
        Criteria cri = new Criteria();
        cri.setPageNum(1);
        cri.setAmount(1);
        cri.setKeyword("송파구");
        cri.setTypeFish("fish");


        service.getAddressWithPagingList(cri);
    }

    @Test
    void getLocationWithPagingList() {
        Criteria cri = new Criteria();
        cri.setPageNum(1);
        cri.setAmount(1);
        cri.setLatitude(37.4943541005291);
        cri.setLongitude(127.116142548584);
        cri.setTypeFish("fish");
        service.getLocationWithPagingList(cri);
    }

    @Test
    void getLocationPagingCnt() {
        Criteria cri = new Criteria();
        cri.setLatitude(37.4943541005291);
        cri.setLongitude(127.116142548584);
        cri.setTypeFish("fish");
        service.getLocationPagingCnt(cri);
    }

    @Test
    void getAddressPagingCnt() {
        Criteria cri = new Criteria();
        cri.setKeyword("송파구");
        cri.setTypeFish("fish");
        service.getAddressPagingCnt(cri);
    }

    @Test
    void getAddressList() {
        service.getAddressList();
    }
}