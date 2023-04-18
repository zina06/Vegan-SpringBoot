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
public class VisitDTO {
    private Integer visitIdx;
    private Integer reserveIdx;
    private Date visitDate;
    private ReviewDTO reviewDTO;
}
