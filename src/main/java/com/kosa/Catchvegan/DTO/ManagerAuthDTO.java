package com.kosa.Catchvegan.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerAuthDTO {
    private Integer manageridx;
    private String auth;
}