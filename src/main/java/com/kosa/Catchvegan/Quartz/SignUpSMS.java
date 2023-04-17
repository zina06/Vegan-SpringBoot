package com.kosa.Catchvegan.Quartz;

import com.kosa.Catchvegan.Mapper.MemberMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SignUpSMS {
    @Value("${twilio.account.sid}")
    private String sid;

    @Value("${twilio.auth.token}")
    private String token;

    @Autowired
    MemberMapper memberMapper;

    // SMS 전송
    public String sendSMS (String phone) {
        Twilio.init(sid,token);
        // 휴대폰 인증번호 생성
        int authNo = randomRange(100000, 999999);
        // 전송대상 휴대폰 번호
        String sendTarget = phone;
        // 전송 메세지
        System.out.println(phone);
        String authMsg = "Catchvegan 회원 가입 인증 번호 [" + authNo + "] 입니다" ;
        Message message = Message.creator(
                // to
                new PhoneNumber(sendTarget),
                // from
                new PhoneNumber("+15074458078"),
                // message
                authMsg).create();
        return String.valueOf(authNo);
    }

    // 인증번호 범위 지정
    public static int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
}
