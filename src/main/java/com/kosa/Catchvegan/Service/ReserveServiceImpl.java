package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.*;
import com.kosa.Catchvegan.Mapper.ReserveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ReserveServiceImpl implements ReserveService{

    @Autowired
    private ReserveMapper mapper;
    @Override
    public RestaurantDTO restaurantDetail(int restaurantIdx) {
        return mapper.restaurantDetail(restaurantIdx);
    }

    @Transactional
    @Override
    public TimeDTO reserveDate(ReserveDTO reserveDTO) {
        int totalResCount18=0;
        int totalResCount1830=0;
        int totalResCount19=0;
        int totalResCount1930=0;
        int totalResCount20=0;
        TimeDTO dto = new TimeDTO();
        RestaurantDTO restaurantDTO = mapper.restaurantDetail(reserveDTO.getRestaurantIdx());
        List<ReserveDTO> reserveList = mapper.reserveDate(reserveDTO);

        List<ReserveDTO> reserve18 = new ArrayList<>(); // 18시에 해당하는 예약 정보 리스트
        List<ReserveDTO> reserve1830 = new ArrayList<>(); // 18시30분에 해당하는 예약 정보 리스트
        List<ReserveDTO> reserve19 = new ArrayList<>(); // 19시에 해당하는 예약 정보 리스트
        List<ReserveDTO> reserve1930 = new ArrayList<>(); // 19시30분에 해당하는 예약 정보 리스트
        List<ReserveDTO> reserve20 = new ArrayList<>(); // 20시에 해당하는 예약 정보 리스트

        for (ReserveDTO reserve : reserveList) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(reserve.getReserveDate());

            int hour = calendar.get(Calendar.HOUR_OF_DAY); // 시간 정보를 가져옴
            int minute = calendar.get(Calendar.MINUTE); // 분 정보를 가져옴

            // 시간 정보에 따라서 해당하는 예약 정보 리스트에 추가
            if (hour == 18 && minute == 0) {
                reserve18.add(reserve);
            } else if (hour == 18 && minute == 30) {
                reserve1830.add(reserve);
            } else if (hour == 19 && minute == 0) {
                reserve19.add(reserve);
            } else if (hour == 19 && minute == 30) {
                reserve1930.add(reserve);
            } else if (hour == 20 && minute == 0) {
                reserve20.add(reserve);
            }
        }
        for (ReserveDTO reserve : reserve18) {
            totalResCount18 += reserve.getResCount();
        }
        for (ReserveDTO reserve : reserve1830) {
            totalResCount1830 += reserve.getResCount();
        }
        for (ReserveDTO reserve : reserve19) {
            totalResCount19 += reserve.getResCount();
        }
        for (ReserveDTO reserve : reserve1930) {
            totalResCount1930 += reserve.getResCount();
        }
        for (ReserveDTO reserve : reserve20) {
            totalResCount20 += reserve.getResCount();
        }
        dto.setSix(restaurantDTO.getLimitPerson()-totalResCount18);
        dto.setSixHalf(restaurantDTO.getLimitPerson()-totalResCount1830);
        dto.setSeven(restaurantDTO.getLimitPerson()-totalResCount19);
        dto.setSevenHalf(restaurantDTO.getLimitPerson()-totalResCount1930);
        dto.setEight(restaurantDTO.getLimitPerson()-totalResCount20);
        return dto;
    }

    @Override
    public Long reserveRes(ReserveDTO reserveDTO) {
        mapper.reserveRes(reserveDTO);
        return reserveDTO.getReserveIdx();
    }

    @Override
    public void reservePay(PaymentDTO paymentDTO) {
        mapper.reservePay(paymentDTO);
    }

    @Override
    public int deleteReserve(ReserveDTO reserveDTO) {
        return mapper.deleteReserve(reserveDTO);
    }

    @Override
    public MemberDTO getMember(int memberIdx) {
        return mapper.getMember(memberIdx);
    }

    @Override
    public PaymentDTO getPayment(PaymentDTO paymentDTO) {
        return mapper.getPayment(paymentDTO);
    }

    @Override
    public int cancelReserve(int reserveIdx) {
        return mapper.cancelReserve(reserveIdx);
    }

    @Override
    public void cancelRes(CancelDTO dto) {
        mapper.cancelRes(dto);
    }

    @Override
    public int deletePayment(ReserveDTO reserveDTO) {
        return mapper.deletePayment(reserveDTO);
    }

    @Override
    public boolean canReserve(ReserveDTO reserveDTO) {
        return mapper.canReserve(reserveDTO).size() == 0 ? true : false ;
    }

    @Override
    public void noReserve() {
        List<ReserveDTO> reserve = mapper.noReserve();
        if(reserve.isEmpty()){
            return;
        }
        else{
            for(ReserveDTO r : reserve){
                System.out.println("delete Reserve" + r);
                mapper.deleteReserve(r);
            }
        }
    }

    @Override
    public void createRefund(RefundDTO refundDTO) {
        mapper.createRefund(refundDTO);
    }

}
