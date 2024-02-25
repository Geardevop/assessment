package com.kbtg.bootcamp.posttest.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/lotteries")
    public ResponseEntity greeting(
            @Validated
            @RequestBody AdminRequest adminRequest) {
        if (!adminRequest.ticket().matches("[0-9]+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket characters should only contain digits 0-9");
        }
        Map<String, Object> body = new HashMap<>();
        body.put("ticket",adminService.createLottery(adminRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

}
