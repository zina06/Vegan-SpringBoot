package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.DTO.ReviewDTO;
import com.kosa.Catchvegan.Service.ReserveService;
import com.kosa.Catchvegan.Service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/Catchvegan")
@Slf4j
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping("/review/{visitIdx}")
    public ResponseEntity<ReviewDTO> reviewGet(@PathVariable int visitIdx){

        return new ResponseEntity<>(service.reviewDetail(visitIdx), HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity reviewCreate(@RequestBody ReviewDTO reviewDTO){
        service.reviewCreate(reviewDTO);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/review")
    public ResponseEntity<ReviewDTO> reviewModify(@RequestBody ReviewDTO reviewDTO) {

        service.reviewUpdate(reviewDTO);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/review/{reviewIdx}")
    public ResponseEntity<ReviewDTO> reviewDelete(@PathVariable int reviewIdx){
        service.reviewDelete(reviewIdx);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
