package com.kosa.Catchvegan.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    private int pageNum;
    private int amount;

    private String keyword = "";

    private String typeVege = "";
    private String typeMilk = "";
    private String typeEgg = "";
    private String typeFish = "";

    private double latitude = 0;
    private double longitude = 0;
    public Criteria() {
        this(1, 6);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}