package com.kbtg.bootcamp.posttest.userTicket;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, CompositeUserTicketKey> {
    @Query("SELECT l FROM UserTicket  l WHERE l.id.userId = :id ")
    List<UserTicket> getUserTicketsById(String id);
    @Transactional
    long deleteUserTicketById_UserIdAndId_LotteryId(String userId, String lotteryId);

}
