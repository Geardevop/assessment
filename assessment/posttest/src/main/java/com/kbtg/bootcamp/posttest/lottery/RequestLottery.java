package com.kbtg.bootcamp.posttest.lottery;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestLottery {
    private String ticket;
    private BigDecimal price;
    private String amount;
}
