package com.kbtg.bootcamp.posttest.userTicket;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositeUserTicketKey implements Serializable {
    @Column(name = "user_id")
    private String userId;
    @Column(name = "lottery_id")
    private String lotteryId;
}
