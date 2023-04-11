package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/Catchvegan/manager/{manageridx}")
    public ResponseEntity<RestaurantDTO> RestaurantManager(@PathVariable int manageridx){
        try {
            RestaurantDTO restaurantDTO = managerService.findrestaurantbymanager(manageridx);

            if (restaurantDTO != null) {
                return new ResponseEntity<RestaurantDTO>(restaurantDTO, HttpStatus.OK);
            }

        } catch (Exception e) {

        }
        return new ResponseEntity<RestaurantDTO>(HttpStatus.NO_CONTENT);
    }


}
