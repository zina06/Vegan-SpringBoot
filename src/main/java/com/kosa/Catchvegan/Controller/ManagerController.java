package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Slf4j
@RestController
@RequestMapping("/Catchvegan")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @PostMapping("/manager/signup")
    public ResponseEntity<String> adminSignup(@RequestBody ManagerDTO managerDTO){
        System.out.println("나는 어드민으로 가입할꺼야");
        managerService.createManager(managerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //식당 정보 불러오기
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

    //식당 정보 수정
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

    //예약자 목록 리스트
    @GetMapping("/manager/reservemember/{restaurantIdx}")
    public ResponseEntity<List<ReserveDTO>> ReserveMemberlist(@PathVariable int restaurantIdx){
        List<ReserveDTO> reservememberlist = managerService.reservememberlist(restaurantIdx);
        try {


            if (reservememberlist != null) {
                return new ResponseEntity<List<ReserveDTO>>(reservememberlist, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<List<ReserveDTO>>(HttpStatus.NO_CONTENT);
    }




    //방문 회원 상태 변경
    @PutMapping("/manager/confirmstatus")
    public ResponseEntity<Void> ConfirmStatus(@RequestBody ReserveDTO reserveDTO){
//        ReserveDTO reserveDTO=new ReserveDTO();
//        reserveDTO.setReserveIdx(reserveIdx);
        try{

            managerService.confirmstatus(reserveDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }

}
