package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.Date;
@Generated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDTO {
    private Long reserveIdx;
    private Integer memberIdx;
    private Integer restaurantIdx;

    private Date reserveDate;
    private Integer resCount;
    private String visitStatus;
    private String cancelStatus;

    private MemberDTO memberDTO;

    private RestaurantDTO restaurantDTO;
    private VisitDTO visitDTO;
}
