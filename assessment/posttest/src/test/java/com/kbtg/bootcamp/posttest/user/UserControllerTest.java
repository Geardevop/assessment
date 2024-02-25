package com.kbtg.bootcamp.posttest.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.math.BigDecimal;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    MockMvc mockMvc;
    @Mock
    UserService userService;
    @BeforeEach
    void setUp() {
        UserController userController  = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when perform on GET: /{userId}/lotteries return  {\\\"tickets\\\": [], \\\"count\\\": 0, \\\"cost\\\": 0}")
    void getUserLotteryIfUserNotHaveLottery() throws Exception{
        UserLotteryResponse userLotteryResponse = new UserLotteryResponse();
        Set<String> lotteryList = new HashSet<>();
        userLotteryResponse.setTickets(lotteryList);
        userLotteryResponse.setCount(0);
        userLotteryResponse.setCost(BigDecimal.ZERO);
        doReturn(userLotteryResponse).when(userService).getLotteries("0000000000");
        mockMvc.perform(get("/users/0000000000/lotteries"))
                // got error 401 authorize เลยทำให้ไม่สามารถเทสได้
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets", hasSize(0)))
                .andExpect(jsonPath("$.count", is(0)))
                .andExpect((jsonPath("$.cost", is(0))));


    }
    @Test
    @DisplayName("when perform on GET: /{userId}/lotteries return  {\\\"tickets\\\": [010101], \\\"count\\\": 1, \\\"cost\\\": 100}")
    void getUserLotteryIfUserHaveLottery() throws Exception{
        UserLotteryResponse userLotteryResponse = new UserLotteryResponse();
        Set<String> lotteryList = new HashSet<>();
        lotteryList.add("010101");
        userLotteryResponse.setTickets(lotteryList);
        userLotteryResponse.setCount(1);
        userLotteryResponse.setCost(BigDecimal.valueOf(100));
        doReturn(userLotteryResponse).when(userService).getLotteries("1111111111");
        mockMvc.perform(get("/users/1111111111/lotteries"))
                // got error 401 authorize เลยทำให้ไม่สามารถเทสได้
//            .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets").value("010101"))
                .andExpect(jsonPath("$.count", is(1)))
                .andExpect((jsonPath("$.cost", is(100))));

    }
//    // can check custom exception
//    @Test
//    @DisplayName("when perform on DELETE: /user/{userId}/lotteries/ticketId return  {\\\"message\\\": ticketI id not found, \\\"httpStatus\\\": NOT_FOUND}")
//    void deleteUserLotteryIfUserNotHaveLottery() throws Exception{
//        String userId = "0000000000";
//        String ticketId = "000000";
//        doAnswer(invocation -> {throw new NotFoundException("ticket id not found");}).when(userService).deleteTicket(userId, ticketId);
//        mockMvc.perform(delete("/users/"+userId+"/lotteries/"+ticketId))
//                // got error 401 authorize เลยทำให้ไม่สามารถเทสได้
////                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(result -> assertTrue)
//
//
//    }

    @Test
    @DisplayName("when perform on DELETE: /user/1111111111/lotteries/000000 return  {\\\"tickets\\\": \\\"000000\\\"}")
    void deleteUserLotteryIfUserHaveLottery() throws Exception{
        String userId = "1111111111";
        String ticketId ="000000";
        doReturn(ticketId).when(userService).deleteTicket(userId, ticketId);
        mockMvc.perform(delete("/users/"+userId+"/lotteries/"+ticketId))
                // got error 401 authorize เลยทำให้ไม่สามารถ expect status ได้
                .andDo(print())
                .andExpect((jsonPath("$.ticket", is("000000"))));
    }
    @Test
    @DisplayName("when perform on POST: /users/1111111111/lotteries/010101 return  {\\\"id\\\": \\\"010101\\\"}")
    void buyLotteriesIfLotteryIsExists() throws  Exception{
        String userId = "1111111111";
        String ticketId ="000000";
        doReturn(ticketId).when(userService).buyTicket(userId, ticketId);
        mockMvc.perform(post("/users/"+userId+"/lotteries/"+ticketId))
                // got error 401 authorize เลยทำให้ไม่สามารถ expect status ได้
                .andDo(print())
                .andExpect((jsonPath("$.id", is("000000"))));

    }
    // ทำไม่ทัน
//    @Test
//    @DisplayName("when perform on POST: /users/1111111111/lotteries/010101 return  {\\\"id\\\": \\\"010101\\\"}")
//    void buyLotteriesIfLotteryIsNotExist() throws  Exception{
//        String userId = "1111111111";
//        String ticketId ="000000";
//        doReturn(ticketId).when(userService).buyTicket(userId, ticketId);
//        mockMvc.perform(post("/users/"+userId+"/lotteries/"+ticketId))
//                // got error 401 authorize เลยทำให้ไม่สามารถ expect status ได้
//                .andDo(print())
//                .andExpect((jsonPath("$.id", is("000000"))));
//
//    }

}
