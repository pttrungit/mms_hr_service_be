package com.mms.hr.controller;

import com.mms.hr.entity.LeaveBalance;
import com.mms.hr.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LeaveBalanceController {
    
    @Autowired
    private LeaveBalanceService leaveBalanceService;
    
    @GetMapping("/leave-balance/{userId}")
    public ResponseEntity<LeaveBalance> getLeaveBalance(@PathVariable Long userId) {
        try {
            int currentYear = LocalDate.now().getYear();
            LeaveBalance balance = leaveBalanceService.getLeaveBalance(userId, currentYear);
            System.out.println("💰 Returning leave balance for user " + userId + ": " + balance.getRemainingDays() + " days");
            return ResponseEntity.ok(balance);
        } catch (RuntimeException e) {
            System.err.println("❌ Leave balance not found for user: " + userId);
            return ResponseEntity.notFound().build();
        }
    }
}
