package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDTO {
    private Integer visitIdx;
    private Integer reserveIdx;
    private Date visitDate;
    private ReviewDTO reviewDTO;
}
