package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;

import java.util.List;

@Mapper
public interface ReviewMapper {

    public List<ReviewDTO> reviewsFindAll();

    public void createReview(ReviewDTO reviewDTO);

    public ReviewDTO detailReview(int visitIdx);

    public void updateReview(ReviewDTO reviewDTO);

    public void deleteReview(int reviewIdx);


}



