package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReviewDTO;
import com.kosa.Catchvegan.Mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewMapper mapper;

    @Override
    public void reviewCreate(ReviewDTO reviewDTO) {
        mapper.createReview(reviewDTO);
    }

    @Override
    public void reviewUpdate(ReviewDTO reviewDTO) {
        mapper.updateReview(reviewDTO);
    }

    @Override
    public void reviewDelete(int reviewIdx) {
        mapper.deleteReview(reviewIdx);
    }

    @Override
    public List<MemberDTO> oneMemberAllReview(MemberDTO memberDTO) {
        return mapper.oneMemberAllReview(memberDTO);
    }

    @Override
    public List<ReviewDTO> getReviewList(int restaurantIdx) {

        return mapper.getReviewList(restaurantIdx);
    }

    @Override
    public List<ReviewDTO> getRecentReview() {
        return mapper.getRecentReview();
    }

}
