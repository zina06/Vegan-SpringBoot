package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Service.ReserveService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Catchvegan")
@Slf4j
public class ReserveController {

    @Autowired
    private ReserveService service;

    @GetMapping("/reserve/{restaurantIdx}")
    public ResponseEntity<RestaurantDTO> reserveGet(@PathVariable int restaurantIdx){

        return new ResponseEntity<>(service.restaurantDetail(restaurantIdx), HttpStatus.OK);
    }

}
