package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDTO {
    private Integer managerIdx;
    private Integer restaurantIdx;
    private String id;
    private String password;

    private List<ManagerAuthDTO> roles;

}
