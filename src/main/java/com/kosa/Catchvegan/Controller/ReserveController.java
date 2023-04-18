package com.kosa.Catchvegan.Controller;

import com.kosa.Catchvegan.DTO.*;
import com.kosa.Catchvegan.Service.KakaoPayService;
import com.kosa.Catchvegan.Service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
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
    @GetMapping("/reserve/{restaurantIdx}/{memberIdx}")
    public ResponseEntity<Object> reserveGet(@PathVariable("restaurantIdx") int restaurantIdx, @PathVariable("memberIdx") int memberIdx){
        Map<String,Object> map = new HashMap();
        map.put("RestaurantDTO",service.restaurantDetail(restaurantIdx));
        map.put("MemberDTO",service.getMember(memberIdx));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //결제요청
    @PostMapping("/reserve/ready/{restaurantIdx}")
    public ResponseEntity readyToKakaoPay(@PathVariable int restaurantIdx, @RequestBody ReserveDTO reserveDTO) {
        RestaurantDTO restaurantDTO = service.restaurantDetail(restaurantIdx);
        int total = reserveDTO.getResCount()*restaurantDTO.getReservePay();
        log.info(reserveDTO.toString());
        Long reserveIdx = service.reserveRes(reserveDTO);

        KakaoReadyResponseDTO response = payService.kakaoPayReady(restaurantDTO.getName(),reserveDTO.getResCount(),total,reserveIdx);
        log.info("================================================" + response);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    //결제성공
    @GetMapping("/reserve/success/{reserveIdx}")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken, @PathVariable Long reserveIdx) throws IOException {

        KakaoApproveResponseDTO kakaoApprove = payService.approveResponse(pgToken, reserveIdx);
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setReserveIdx(Integer.parseInt(kakaoApprove.getPartner_order_id()));
        paymentDTO.setTid(kakaoApprove.getTid());
        paymentDTO.setPayInfo("");
        paymentDTO.setPayAmount(kakaoApprove.getAmount().getTotal());
        service.reservePay(paymentDTO);
        Collection<SseEmitter> sseEmitters = emitters.values();
        Iterator<SseEmitter> iterator = sseEmitters.iterator();

        while(iterator.hasNext()){
            SseEmitter emitter = iterator.next();

            try {
                System.out.println("SSE 시도");
                emitter.send(SseEmitter.event()
                        .name("paymentResult")
                        .data("success"));
                System.out.println("SSE 성공");
                emitter.complete();
            } catch (Exception e) {
                // SseEmitter가 끊어진 경우, 예외 처리
                System.out.println("SSE 실패");
                iterator.remove();
                System.out.println("SSE 삭제");
            }

            ReserveDTO dto = new ReserveDTO();
            dto.setReserveIdx(reserveIdx);
        }

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    //SSE

    @GetMapping("/reserve-result")
    public SseEmitter getPaymentResult() {
        System.out.println("SSE 연결요청");
        SseEmitter emitter = new SseEmitter(3600000L);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitters.put(UUID.randomUUID().toString(), emitter);
        return emitter;
    }

    @GetMapping("/reserve/cancel/{reserveIdx}")
    public void cancel(@PathVariable Long reserveIdx) {
        ReserveDTO dto = new ReserveDTO();
        dto.setReserveIdx(reserveIdx);
        service.deleteReserve(dto);
        throw new RuntimeException("결제취소");
    }

    @GetMapping("/reserve/fail/{reserveIdx}")
    public void fail(@PathVariable Long reserveIdx) throws IOException {
        ReserveDTO dto = new ReserveDTO();
        dto.setReserveIdx(reserveIdx);
        service.deleteReserve(dto);

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
    @PostMapping("/reserve/getTime")
    public ResponseEntity canReserve(@RequestBody ReserveDTO reserveDTO){
        if(service.canReserve(reserveDTO)){
            return new ResponseEntity<>(service.reserveDate(reserveDTO),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("alreadyReserve",HttpStatus.OK);
        }
    }

    //예약취소및환불
    @PostMapping("/reserve/refund")
    public ResponseEntity<KakaoCancelResponseDTO> refund(@RequestBody PaymentDTO paymentDTO){
        System.out.println(paymentDTO);
        KakaoCancelResponseDTO kakaoCancelResponse = payService.kakaoCancel(paymentDTO);

        return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);

    }
}
