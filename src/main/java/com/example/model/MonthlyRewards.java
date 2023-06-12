package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class MonthlyRewards {

    private String month;
    private Integer rewards;


}
