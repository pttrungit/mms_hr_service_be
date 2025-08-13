package com.mms.hr.service;

import com.mms.hr.entity.LeaveBalance;
import com.mms.hr.repository.LeaveBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeaveBalanceService {
    
    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;
    
    public LeaveBalance getLeaveBalance(Long userId, Integer year) {
        return leaveBalanceRepository.findByUserIdAndYear(userId, year)
                .orElseThrow(() -> new RuntimeException("Leave balance not found for user " + userId + " in year " + year));
    }
    
    public List<LeaveBalance> getLeaveBalancesByUserId(Long userId) {
        return leaveBalanceRepository.findByUserId(userId);
    }
    
    public LeaveBalance createOrUpdateLeaveBalance(LeaveBalance leaveBalance) {
        return leaveBalanceRepository.save(leaveBalance);
    }
    
    public void updateBalanceAfterLeaveApproval(Long userId, Integer year, Integer days) {
        LeaveBalance balance = getLeaveBalance(userId, year);
        balance.setUsedDays(balance.getUsedDays() + days);
        balance.setRemainingDays(balance.getTotalDays() - balance.getUsedDays());
        leaveBalanceRepository.save(balance);
    }
}
