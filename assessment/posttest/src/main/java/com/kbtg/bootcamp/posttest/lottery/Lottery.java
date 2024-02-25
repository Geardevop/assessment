package com.kbtg.bootcamp.posttest.lottery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kbtg.bootcamp.posttest.userTicket.UserTicket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="lottery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lottery {
    @Id
    private String id;
    @Column
    private BigDecimal price;
    @Column
    private int amount;
    @OneToMany(mappedBy = "id.lotteryId", cascade = CascadeType.REMOVE)
    private List<UserTicket> userTickets;
}
