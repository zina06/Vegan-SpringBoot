package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDTO {
    private Integer reserveIdx;
    private Integer memberIdx;
    private Integer restaurantIdx;
    private Date reserveDate;
    private Integer resCount;
    private String visitStatus;
    private String cancelStatus;
}
