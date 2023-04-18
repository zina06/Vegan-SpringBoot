package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReserveDTO;
import com.kosa.Catchvegan.DTO.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;

import java.lang.reflect.Member;
import java.util.List;

@Mapper
public interface ReviewMapper {

    public List<ReviewDTO> reviewsFindAll();

    public void createReview(ReviewDTO reviewDTO);

    public ReviewDTO detailReview(int visitIdx);

    public void updateReview(ReviewDTO reviewDTO);

    public void deleteReview(int reviewIdx);

    public List<MemberDTO> oneMemberAllReview(MemberDTO memberDTO);


}



