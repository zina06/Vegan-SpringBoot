package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Integer payIdx;
    private Integer reserveIdx;
    private Integer payAmount;
    private Date payDate;
    private String payInfo;
}
