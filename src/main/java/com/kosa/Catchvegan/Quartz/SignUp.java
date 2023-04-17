package com.kosa.Catchvegan.Quartz;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.Mapper.MemberMapper;
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

import java.lang.reflect.Member;
import java.util.Date;
import java.util.List;
@Component
public class SignUp {
    public static final String sid = "${twilio.account.sid}";
    public static final String token = "${twilio.auth.token}";

    @Autowired
    MemberMapper memberMapper;

    // SMS 전송
    public static int sendSMS (String phone) {
        Twilio.init(sid,token);
        // 휴대폰 인증번호 생성
        int authNo = randomRange(100000, 999999);
        // 전송대상 휴대폰 번호
        String sendTarget = phone;
        // 전송 메세지
        String authMsg = "Catchvegan 회원 가입 인증 번호 [" + authNo + "] 입니다" ;
        Message message = Message.creator(
                // to
                new PhoneNumber(sendTarget),
                // from
                new PhoneNumber("+15074458078"),
                // message
                authMsg).create();
        return authNo;
    }

    // 인증번호 범위 지정
    public static int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
}
