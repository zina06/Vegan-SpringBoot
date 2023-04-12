package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Integer memberidx;
    private String id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String vegantype;
    private String withdraw;

//    private List<MemberAuthDTO> roles;
}
