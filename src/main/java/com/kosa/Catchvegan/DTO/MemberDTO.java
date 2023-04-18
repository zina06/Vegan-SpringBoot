package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Integer memberIdx;
    private String id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String veganType;
    private String withdraw;

    private List<MemberAuthDTO> roles;
    private List<ReviewDTO> reviewDTOList; // 추가

}
