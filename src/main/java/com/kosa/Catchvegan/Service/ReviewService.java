package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.MemberDTO;
import com.kosa.Catchvegan.DTO.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public ReviewDTO reviewDetail(int visitIdx);

    public void reviewCreate(ReviewDTO reviewDTO);

    public void reviewUpdate(ReviewDTO reviewDTO);

    public void reviewDelete(int reviewIdx);

    public List<MemberDTO> oneMemberAllReview(MemberDTO memberDTO);
}
