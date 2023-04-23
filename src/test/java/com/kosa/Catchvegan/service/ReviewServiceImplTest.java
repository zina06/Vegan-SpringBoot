package com.kosa.Catchvegan.service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReviewDTO;
import com.kosa.Catchvegan.Service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceImplTest {

    @Autowired
    private ReviewService service;

    @Test
    void reviewCreate() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle("테스트 타이틀");
        reviewDTO.setContent("테스트 컨텐츠");
        reviewDTO.setRating(3.0);
        reviewDTO.setImages("테스트 이미지");
        reviewDTO.setVisitIdx(4);
        service.reviewCreate(reviewDTO);
    }

    @Test
    void reviewUpdate() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewIdx(1);
        reviewDTO.setTitle("테스트 제목");
        reviewDTO.setContent("테스트 내용");
        reviewDTO.setRating(1);
        reviewDTO.setImages("테스트 이미지");


        service.reviewUpdate(reviewDTO);
    }

    @Test
    void reviewDelete() {
        service.reviewDelete(1);
    }

    @Test
    void oneMemberAllReview() {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberIdx(1);

        service.oneMemberAllReview(memberDTO);


    }

    @Test
    void getReviewList() {
        service.getReviewList(1);
    }

    @Test
    void getRecentReview() {

        service.getRecentReview();
    }
}