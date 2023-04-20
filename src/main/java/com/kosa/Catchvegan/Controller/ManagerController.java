package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.DTO.VisitDTO;
import com.kosa.Catchvegan.Mapper.ReserveMapper;
import com.kosa.Catchvegan.Service.ManagerService;
import com.kosa.Catchvegan.Service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;


@Slf4j
@RestController
@RequestMapping("/Catchvegan")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @PostMapping ("/manager/aftermanager")
    public String afterMember(){
        return "여기는 어드민 토큰있는사람만 올수있어";
    }


    //식당 정보 불러오기

//    @GetMapping("/manager/{managerIdx}")
//    public Map<String, Object> RestaurantManager(@PathVariable int managerIdx){
//        Map<String, Object> map=new HashMap<>();
//        RestaurantDTO restaurantDTO = managerService.restaurantmanage(managerIdx);
//        List<ReserveDTO> reservememberlist = managerService.reservememberlist(restaurantDTO.getRestaurantIdx());
//        map.put("restaurantDTO",restaurantDTO);
//        map.put("reservelist",reservememberlist);
//
//        return map;
//    }
    @PostMapping("/manager/signup")
    public ResponseEntity<String> adminSignup(@RequestBody ManagerDTO managerDTO){
        System.out.println("나는 어드민으로 가입할꺼야");
        managerService.createManager(managerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

  

    @GetMapping("/manager/{managerIdx}")
    public Map<String, Object> RestaurantManager(@PathVariable int managerIdx, @RequestParam String reserveDate){
        Map<String, Object> map=new HashMap<>();
        RestaurantDTO restaurantDTO = managerService.getOneRestaurant(managerIdx);
        System.out.println(reserveDate);
        List<ReserveDTO>reserveDTOS1=managerService.findAllReserve(restaurantDTO);
        try{
            String year = reserveDate.substring(0, 4); // 2023
            String month = reserveDate.substring(5, 7); // 04
            String day = reserveDate.substring(8, 10); // 13
            System.out.println(year + "-" + month + "-" + day);
            ReserveDTO reserveDTO = new ReserveDTO();
            reserveDTO.setReserveDate(new Date(parseInt(year)-1900,parseInt(month)-1,parseInt(day)));
            reserveDTO.setRestaurantIdx(restaurantDTO.getRestaurantIdx());
            List<ReserveDTO>reserveDTOS=managerService.reserveMemberList(reserveDTO);   //날짜에 따른 예약목록

//            map.put("restaurantDTO",restaurantDTO);
            map.put("reservelist",reserveDTOS);
        }
        catch(Exception e){
//            e.printStackTrace();
        }
        finally {


                map.put("restaurantDTO",restaurantDTO);
                map.put("all", reserveDTOS1);


        }



        return map;
    }



    //식당 정보 수정
    @PutMapping("/managerupdate")
    public ResponseEntity<Void> RestaurantUpdate(@RequestBody RestaurantDTO restaurantDTO){
        try{
//            restaurantDTO.setRestaurantIdx(restaurantIdx);
            managerService.updateRestaurant(restaurantDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }

    //예약자 목록 리스트
//    @GetMapping("/manager/reservemember/{restaurantIdx}")
//    public ResponseEntity<List<ReserveDTO>> ReserveMemberlist(@PathVariable int restaurantIdx){
//        List<ReserveDTO> reservememberlist = managerService.reservememberlist(restaurantIdx);
//        try {
//
//
//            if (reservememberlist != null) {
//                return new ResponseEntity<List<ReserveDTO>>(reservememberlist, HttpStatus.OK);
//            }
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return new ResponseEntity<List<ReserveDTO>>(HttpStatus.NO_CONTENT);
//    }




    //방문 회원 상태 변경
    @PutMapping ("/manager/confirmStatus")
    public ResponseEntity<Void> ConfirmStatus(@RequestBody ReserveDTO reserveDTO){
//        ReserveDTO reserveDTO=new ReserveDTO();
//        reserveDTO.setReserveIdx(reserveIdx);
        try{

            managerService.confirmStatus(reserveDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }

    //방문확정 후 visitDTO생성
    @PostMapping("/manager/createVisit")
    public ResponseEntity<Void> createVisit(@RequestBody VisitDTO visitDTO){
        try{

            managerService.createVisit(visitDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
