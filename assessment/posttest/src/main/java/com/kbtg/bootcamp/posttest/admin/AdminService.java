package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    private final LotteryRepository lotteryRepository;

    public AdminService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }
    public String createLottery(AdminRequest adminRequest) {
        Lottery lotteryExits = lotteryRepository.findLotteryById(adminRequest.ticket());
        Map<String, List<String>> keyValueTicket = new HashMap<>();
        // check is lottery is already exits in database
        if(lotteryExits != null ){
            // increase amount
            lotteryExits.setAmount(
                    Integer.valueOf(lotteryExits.getAmount()) +Integer.valueOf( adminRequest.amount()));
            // update price
            lotteryExits.setPrice(
                    adminRequest.price()
            );
            return  lotteryRepository.save(lotteryExits).getId();
        }else{
            Lottery newLottery = new Lottery();
            newLottery.setId(adminRequest.ticket());
            newLottery.setPrice(adminRequest.price());
            newLottery.setAmount(Integer.valueOf(adminRequest.amount()));
            return lotteryRepository.save(newLottery).getId();
        }
    }
}
