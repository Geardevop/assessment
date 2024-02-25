package com.kbtg.bootcamp.posttest.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLotteryResponse {
    private Set<String> tickets;
    private int count;
    private BigDecimal cost;
}
