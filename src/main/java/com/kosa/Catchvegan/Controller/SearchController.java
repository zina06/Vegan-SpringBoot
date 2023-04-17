package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.Criteria;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/Catchvegan/search")
@Slf4j
public class SearchController {
    @Autowired
    private SearchService service;

    @GetMapping("/")
    public ResponseEntity<List<RestaurantDTO>>  restaurantGet(){
        return new ResponseEntity<>(service.restaurantAllShow(),HttpStatus.OK );
    }

    @GetMapping("/address")
    public List<RestaurantDTO>  restaurantAddressPagingGet(Criteria cri, Model model){
        model.addAttribute("list", service.getAddressWithPagingList(cri));
        return service.getAddressWithPagingList(cri);
    }

    @GetMapping("/location")
    public List<RestaurantDTO>  restaurantLocationPagingGet(Criteria cri, Model model){
        model.addAttribute("list", service.getLocationWithPagingList(cri));
        return service.getLocationWithPagingList(cri);
    }

    @GetMapping("/count/address")
    public Integer  restaurantAddressCount(Criteria cri, Model model){
        model.addAttribute("list", service.getAddressPagingCnt(cri));
        return service.getAddressPagingCnt(cri);
    }
    @GetMapping("/count/location")
    public Integer  restaurantLocationCount(Criteria cri, Model model){
        model.addAttribute("list", service.getLocationPagingCnt(cri));
        return service.getLocationPagingCnt(cri);
    }

    @GetMapping("/addressList")
    public List<String> addressListGet(){
        return service.getAddressList();
    }

}
