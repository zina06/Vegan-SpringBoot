package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundDTO {
    private Integer refundIdx;
    private Integer payIdx;
    private Integer reserveIdx;
    private Integer refundAmount;
    private Date refundDate;
    private String refundInfo;
}
