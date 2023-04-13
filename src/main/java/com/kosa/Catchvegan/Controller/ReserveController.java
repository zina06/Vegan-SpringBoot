package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.*;
import com.kosa.Catchvegan.Service.KakaoPayService;
import com.kosa.Catchvegan.Service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/Catchvegan")
@Slf4j
public class ReserveController {

    @Autowired
    private ReserveService service;

    @Autowired
    private KakaoPayService payService;

    //SSE
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @ResponseBody
    @GetMapping("/reserve/{restaurantIdx}")
    public ResponseEntity<RestaurantDTO> reserveGet(@PathVariable int restaurantIdx){

        return new ResponseEntity<>(service.restaurantDetail(restaurantIdx), HttpStatus.OK);
    }

    //결제요청
    @PostMapping("/reserve/ready/{restaurantIdx}")
    public ResponseEntity readyToKakaoPay(@PathVariable int restaurantIdx, @RequestBody ReserveDTO reserveDTO) {
        RestaurantDTO restaurantDTO = service.restaurantDetail(restaurantIdx);
        int total = reserveDTO.getResCount()*restaurantDTO.getReservePay();

        KakaoReadyResponseDTO response = payService.kakaoPayReady(restaurantDTO.getName(),reserveDTO.getResCount(),total);
        log.info("================================================" + response);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    //결제성공
    @GetMapping("/reserve/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) throws IOException {

        KakaoApproveResponseDTO kakaoApprove = payService.approveResponse(pgToken);

        Collection<SseEmitter> sseEmitters = emitters.values();
        Iterator<SseEmitter> iterator = sseEmitters.iterator();

        while(iterator.hasNext()){
            SseEmitter emitter = iterator.next();
            emitter.send(SseEmitter.event()
                    .name("paymentResult")
                    .data("success"));
            emitter.complete();
            iterator.remove();
        }

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    //SSE
    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/reserve-result")
    public SseEmitter getPaymentResult() {
        log.info("SSESSESSESSESSESSESSESSE");
        SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitters.put(UUID.randomUUID().toString(), emitter);
        return emitter;
    }

    @GetMapping("/reserve/cancel")
    public void cancel() {

        throw new RuntimeException("결제취소");
    }

    @GetMapping("/reserve/fail")
    public void fail() throws IOException {
        Collection<SseEmitter> sseEmitters = emitters.values();
        Iterator<SseEmitter> iterator = sseEmitters.iterator();

        while(iterator.hasNext()){
            SseEmitter emitter = iterator.next();
            emitter.send(SseEmitter.event()
                    .name("paymentResult")
                    .data("fail"));
            emitter.complete();
            iterator.remove();
        }

        throw new RuntimeException("결제실패");
    }


    //달력에 맞는 시간 보내주기
    @ResponseBody
    @PostMapping("/reserve/getTime")
    public ResponseEntity<TimeDTO> canReserve(@RequestBody ReserveDTO reserveDTO){
        System.out.println(reserveDTO);
        return new ResponseEntity<>(service.reserveDate(reserveDTO),HttpStatus.OK);
    }
}
