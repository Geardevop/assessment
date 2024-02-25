package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, String> {
    @Query("SELECT l FROM Lottery l WHERE l.id = :id")
    Lottery findLotteryById(String id);
}
