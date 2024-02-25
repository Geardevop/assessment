package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.userTicket.CompositeUserTicketKey;
import com.kbtg.bootcamp.posttest.userTicket.UserTicket;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LotteryRepository lotteryRepository;
    private final UserTicketRepository userTicketRepository;

    public UserService(UserRepository userRepository, LotteryRepository lotteryRepository, UserTicketRepository userTicketRepository){
        this.userRepository = userRepository;
        this.lotteryRepository = lotteryRepository;
        this.userTicketRepository = userTicketRepository;
    }
    @Transactional
    public String buyTicket(String  userId, String ticketId){
        User exitsUser = userRepository.findUserById(userId);
        Lottery exitsLottery = lotteryRepository.findLotteryById(ticketId);
        UserTicket newUserTicket = new UserTicket();
        CompositeUserTicketKey compositeUserTicketKey = new CompositeUserTicketKey();
        // check is user and lottery is exits
        if(exitsUser != null && exitsLottery != null){
            // perform data to table user_ticket
            compositeUserTicketKey.setUserId(exitsUser.getId());
            compositeUserTicketKey.setLotteryId(exitsLottery.getId());
            newUserTicket.setId(compositeUserTicketKey);
            newUserTicket.setLottery_amount(1);
            newUserTicket.setCost(exitsLottery.getPrice());
            userTicketRepository.save(newUserTicket);
            return ticketId;
        }
        else if(exitsUser == null && exitsLottery!= null){
            User user = new User();
            user.setId(userId);
            userRepository.save(user);
            compositeUserTicketKey.setUserId(user.getId());
            compositeUserTicketKey.setLotteryId(exitsLottery.getId());
            newUserTicket.setId(compositeUserTicketKey);
            newUserTicket.setLottery_amount(1);
            newUserTicket.setCost(exitsLottery.getPrice());
            userTicketRepository.save(newUserTicket);
            return ticketId;

        }else{
            throw new NotFoundException("lottery id is not found");
        }
    }

    public UserLotteryResponse getLotteries(String userId){
        User user = userRepository.findUserById(userId);
        UserLotteryResponse userLotteryResponse = new UserLotteryResponse();
        Set<String> lotteryList = new HashSet<>();
        if(user != null){
            List<UserTicket> exitsUserTicket = userTicketRepository.getUserTicketsById(userId);
            BigDecimal cost = BigDecimal.ZERO;
            int count = 0;
            for (UserTicket userTicket : exitsUserTicket) {
                lotteryList.add(userTicket.getId().getLotteryId());
                cost = cost.add(userTicket.getCost());
                count = count + userTicket.getLottery_amount();

            }
            userLotteryResponse.setTickets(lotteryList);
            userLotteryResponse.setCount(count);
            userLotteryResponse.setCost(cost);

          return userLotteryResponse;

        }else {
            userLotteryResponse.setTickets(Collections.EMPTY_SET);
            userLotteryResponse.setCost(BigDecimal.ZERO);
            return userLotteryResponse;
        }

    }
    public  String  deleteTicket(String userId, String ticketId){
        long numberOfTicketDeleted;
        numberOfTicketDeleted = userTicketRepository.deleteUserTicketById_UserIdAndId_LotteryId(userId, ticketId);
        if(numberOfTicketDeleted== 0){
            throw new NotFoundException("ticket id not found");
        }
        return ticketId;
    }

}
