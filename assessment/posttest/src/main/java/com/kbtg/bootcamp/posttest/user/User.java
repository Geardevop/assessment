package com.kbtg.bootcamp.posttest.user;
import com.kbtg.bootcamp.posttest.userTicket.UserTicket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    @OneToMany(mappedBy = "id.userId", cascade = CascadeType.REMOVE)
    private List<UserTicket> userTickets;

}
