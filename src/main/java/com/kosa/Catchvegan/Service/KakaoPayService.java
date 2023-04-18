package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.apache.logging.log4j.core.util.Integers.parseInt;

@Service
public class KakaoPayService {

    @Autowired
    private ReserveService service;
    private KakaoReadyResponseDTO kakaoReady;

    @Transactional
    public KakaoReadyResponseDTO kakaoPayReady(String name, int persons, int total, Long reserveIdx){

        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("partner_order_id", Long.toString(reserveIdx));
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("item_name", name);
        parameters.add("quantity", Integer.toString(persons));
        parameters.add("total_amount", Integer.toString(total));
        parameters.add("vat_amount", "0");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8082/Catchvegan/reserve/success/"+Long.toString(reserveIdx)); // 성공 시 redirect url
        parameters.add("cancel_url", "http://localhost:8082/Catchvegan/reserve/cancel/"+Long.toString(reserveIdx)); // 취소 시 redirect url
        parameters.add("fail_url", "http://localhost:8082/Catchvegan/reserve/fail/"+Long.toString(reserveIdx)); // 실패 시 redirect url

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponseDTO.class);

        return kakaoReady;
    }

    @Transactional
    public KakaoApproveResponseDTO approveResponse(String pgToken, Long reserveIdx) {

        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", Long.toString(reserveIdx));
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponseDTO approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponseDTO.class);

        return approveResponse;
    }

    /**
     * 결제 환불
     */
    @Transactional
    public KakaoCancelResponseDTO kakaoCancel(PaymentDTO DTO) {
        PaymentDTO paymentDTO = service.getPayment(DTO);
        service.cancelReserve(paymentDTO.getReserveIdx());
        CancelDTO cancelDTO =  new CancelDTO();
        cancelDTO.setReserveIdx(paymentDTO.getReserveIdx());
        service.cancelRes(cancelDTO);
        // 카카오페이 요청
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", paymentDTO.getTid().toString());
        parameters.add("cancel_amount", paymentDTO.getPayAmount());
        parameters.add("cancel_tax_free_amount", 0);


        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoCancelResponseDTO cancelResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/cancel",
                requestEntity,
                KakaoCancelResponseDTO.class);

        return cancelResponse;
    }


    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + "9b90efd1e94c037cb3193e431cc67e1f";

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }

}
