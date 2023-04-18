package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import java.util.List;


@Generated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Integer reviewIdx;
    private Integer visitIdx;
    private String title;
    private String content;
    private double rating;
    private String images;

    private MemberDTO memberDTO;

    private RestaurantDTO restaurantDTO;
}
