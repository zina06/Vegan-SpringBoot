package com.kosa.Catchvegan.mapper;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.DTO.ReviewDTO;
import com.kosa.Catchvegan.Mapper.ManagerMapper;
import com.kosa.Catchvegan.Mapper.ReviewMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerTests {
	@Autowired
	private ReviewMapper reviewMapper;

	@Autowired
	private ManagerMapper managerMapper;

//	@Test
//	void contextLoads() {
//
//		System.out.println(reviewMapper.getReviewList(1));
//
//	}

//	@Test
//	void contextLoads(){
//		// 예약 정보를 검색할 때 사용할 ReserveDTO 객체 생성
//		ReserveDTO reserveDTO = new ReserveDTO(1L, 1, 1, new Date(), 2, "x", "x",new MemberDTO(),new RestaurantDTO());
//
//		// managerMapper.reservememberlist() 메소드 호출
//		List<ReserveDTO> reserveList = managerMapper.reservememberlist(reserveDTO);
//
//		// 검색된 예약 정보 출력
//		for (ReserveDTO reserve : reserveList) {
//			System.out.println(reserve);
//		}
//	}
		@Test
		public void contextLoads(){

			ReviewDTO reviewDTO = new ReviewDTO();
			List<ReviewDTO> recentReviews = reviewMapper.getRecentReview();

			System.out.println(recentReviews);

		}

}
