package com.kbtg.bootcamp.posttest.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<Object> buyUserTicket(
            @PathVariable String userId,
            @PathVariable String ticketId) {
            if (!userId.matches("[0-9]+") || userId.length() != 10) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID should contain exactly 10 digits and contain 0-9");
            }
            Map<String, Object> body = new HashMap<>();
            body.put("id", userService.buyTicket(userId, ticketId));
            return ResponseEntity.ok().body(body);
    }
    @GetMapping(value = "/{userId}/lotteries")
    public ResponseEntity<Object>  getLotteries(
            @PathVariable String userId
    ){
        if (!userId.matches("[0-9]+") || userId.length() != 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID should contain exactly 10 digits and contain 0-9");
        }
        return ResponseEntity.ok().body(userService.getLotteries(userId));

    }
    @DeleteMapping(value = "/{userId}/lotteries/{ticketId}")
    public ResponseEntity<Object> deleteLotteries(
            @PathVariable String userId,
            @PathVariable String ticketId
    ){
        if (!userId.matches("[0-9]+") || userId.length() != 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID should contain exactly 10 digits and contain 0-9");
        }
        Map<String, Object> body = new HashMap<>();
        body.put("ticket",  userService.deleteTicket(userId, ticketId));
        return ResponseEntity.ok().body(body);
    }



}
