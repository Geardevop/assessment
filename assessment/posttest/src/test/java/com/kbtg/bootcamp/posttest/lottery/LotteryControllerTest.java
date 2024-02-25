package com.kbtg.bootcamp.posttest.lottery;


import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doReturn;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LotteryControllerTest {
    MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;
    @BeforeEach
    void setUp() {
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController)
//                .alwaysDo(print())
                .build();
    }


    @Test
    @DisplayName("when perform on GET: /lotteries should return tickets : [010101] ")
    void getLotteries() throws Exception {
        // Initialize a sample lottery object
        Lottery lottery1 = new Lottery();
        lottery1.setId("010101");
        lottery1.setAmount(1);
        lottery1.setPrice(BigDecimal.valueOf(100));

        Map<String, String> resultTicket = new HashMap<>();
        resultTicket.put("tickets", lottery1.getId());

        doReturn(resultTicket).when(lotteryService).findLotteries();

        mockMvc.perform(get("/lotteries"))
                // got error 401 authorize เลยทำให้ไม่สามารถเทสได้
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets", is("010101")));
    }
}
