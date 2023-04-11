package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/Catchvegan")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @GetMapping("/manager/{managerIdx}")
    public ResponseEntity<RestaurantDTO> RestaurantManager(@PathVariable int managerIdx){
        try {
            RestaurantDTO restaurantDTO = managerService.restaurantmanage(managerIdx);

            if (restaurantDTO != null) {
                return new ResponseEntity<RestaurantDTO>(restaurantDTO, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<RestaurantDTO>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/managerupdate")
    public ResponseEntity<Void> RestaurantUpdate(@RequestBody RestaurantDTO restaurantDTO){
        try{
//            restaurantDTO.setRestaurantIdx(restaurantIdx);
            managerService.updaterestaurant(restaurantDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }
}
