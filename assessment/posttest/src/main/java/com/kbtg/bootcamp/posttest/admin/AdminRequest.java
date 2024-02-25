package com.kbtg.bootcamp.posttest.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AdminRequest (
        @NotNull
        @Size(min=6, max = 6, message = "Ticket should be 6 charactor")
        String ticket,
        @NotNull
        BigDecimal price,
        @NotNull
        Integer amount
){

}
