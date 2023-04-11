package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelDTO {
    private Integer cancelIdx;
    private Integer reserveIdx;
    private Date cancelDate;
    private String refundStatus;
}
