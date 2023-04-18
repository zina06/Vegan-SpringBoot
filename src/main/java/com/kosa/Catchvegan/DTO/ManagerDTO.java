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
public class ManagerDTO {
    private Integer managerIdx;
    private Integer restaurantIdx;
    private String id;
    private String password;

    private List<ManagerAuthDTO> roles;

}
