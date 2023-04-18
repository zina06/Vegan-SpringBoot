package com.kosa.Catchvegan.Quartz;

import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.Mapper.ReserveMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReserveJob extends QuartzJobBean {
    @Autowired
    private ReserveMapper reserveMapper;

    @Value("${twilio.account.sid}")
    private String sid;

    @Value("${twilio.auth.token}")
    private String token;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // Reserve 테이블에서 오늘 날짜와 같은 예약 정보 조회하기
        Date today = new Date();
        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setReserveDate(today);
        List<ReserveDTO> reservationList = reserveMapper.reserveDateAlert(reserveDTO);
        // 예약 정보가 있다면, 알림을 보내는 로직을 구현합니다.
        if (!reservationList.isEmpty()) {
            for (ReserveDTO reservation : reservationList) {
                System.out.println(reservation);
                sendNotification(reservation.getMemberDTO().getPhone(), "예약알림", "예약내역을 확인해주세요.");
            }
        }else{
            System.out.println("======================예약이 없습니다만..?");
        }
    }

    private void sendNotification(String phoneNumber, String title, String content) {
        // Twilio API를 사용하여 문자 메시지를 보냅니다.
        System.out.println("Notification sent to " + phoneNumber + ": " + title + " - " + content);
        System.out.println(sid +"====================" +token);
        Twilio.init(sid, token);
        Message message = Message.creator(
                        new PhoneNumber(phoneNumber), // 수신자 전화번호
                        new PhoneNumber("+15074458078"), // 발신자 전화번호
                        content) // 메시지 내용
                .create();
        System.out.println("Notification sent to " + phoneNumber + ": " + title + " - " + content);
    }

}