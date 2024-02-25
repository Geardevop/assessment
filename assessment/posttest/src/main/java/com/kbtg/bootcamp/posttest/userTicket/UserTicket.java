package com.kbtg.bootcamp.posttest.userTicket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="user_ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTicket implements Serializable {
    @EmbeddedId
    private CompositeUserTicketKey id;

    @Column
    private Integer lottery_amount;

    @Column
    private BigDecimal cost;


}
