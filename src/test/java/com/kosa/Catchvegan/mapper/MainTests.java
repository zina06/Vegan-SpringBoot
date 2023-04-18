package com.kosa.Catchvegan.mapper;

import com.kosa.Catchvegan.DTO.ReviewDTO;
import com.kosa.Catchvegan.Mapper.ManagerMapper;
import com.kosa.Catchvegan.Mapper.ReviewMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MainTests {
	@Autowired
	private ReviewMapper reviewMapper;


//	@Test
//	void contextLoads() {
//
//		System.out.println(reviewMapper.getReviewList(1));
//
//	}

	@Test
	void contextLoads(){

		ReviewDTO reviewDTO = new ReviewDTO();
		List<ReviewDTO> recentReviews = reviewMapper.getRecentReview();

		System.out.println(recentReviews);

	}

}
