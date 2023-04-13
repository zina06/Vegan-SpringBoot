package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.Service.MyDiningService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyDiningServiceTest {
    @Autowired
    private MyDiningService service;
    @Test
    public void test(){
        MemberDTO dto = new MemberDTO();
        dto.setMemberIdx(1);
        System.out.println(service.getReserves(dto));
    }
}
