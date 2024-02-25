package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public Map<String, List<String>> findLotteries(){
        Map<String, List<String>> keyValueTicket = new HashMap<>();
        List<Lottery> listLottery = lotteryRepository.findAll();
        List<String> lotteryId = new ArrayList<>();
        if(listLottery.size()>0){
            for(int i = 0; i < listLottery.size(); i++){
                    lotteryId.add(listLottery.get(i).getId());
            }
        }
        keyValueTicket.put("tickets", lotteryId);
        return keyValueTicket;
    }

}
