package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.Service.ReserveService;
import com.kosa.Catchvegan.Service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/Catchvegan")
@Controller
public class RestaurantController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReserveService reserveService;


    @GetMapping("/restaurant/get/{restaurantIdx}")
    public Map<String, Object> getDetail(@PathVariable int restaurantIdx){
        Map<String, Object> map=new HashMap<>();
        map.put("review", reviewService.getReviewList(restaurantIdx));
        map.put("detail", reserveService.restaurantDetail(restaurantIdx));

        return map;
    }

}
