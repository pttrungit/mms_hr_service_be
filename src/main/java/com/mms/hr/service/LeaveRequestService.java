package com.mms.hr.service;

import com.mms.hr.entity.LeaveRequest;
import com.mms.hr.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LeaveRequestService {
    
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;
    
    @Autowired
    private LeaveBalanceService leaveBalanceService;
    
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAllOrderByRequestDateDesc();
    }
    
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found with id: " + id));
    }
    
    public List<LeaveRequest> getLeaveRequestsByUserId(Long userId) {
        return leaveRequestRepository.findByUserIdOrderByRequestDateDesc(userId);
    }
    
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        if (leaveRequest.getEndDate().isBefore(leaveRequest.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        
        if (leaveRequest.getRequestDate() == null) {
            leaveRequest.setRequestDate(LocalDate.now());
        }
        
        if (leaveRequest.getStatus() == null) {
            leaveRequest.setStatus("PENDING");
        }
        
        if (leaveRequest.getTotalDays() == null) {
            long daysBetween = leaveRequest.getStartDate().until(leaveRequest.getEndDate()).getDays() + 1;
            leaveRequest.setTotalDays((int) daysBetween);
        }
        
        return leaveRequestRepository.save(leaveRequest);
    }
    
    public LeaveRequest updateRequestStatus(Long requestId, String status) {
        LeaveRequest request = getLeaveRequestById(requestId);
        
        String oldStatus = request.getStatus();
        request.setStatus(status);
        
        if ("APPROVED".equals(status)) {
            request.setApprovedAt(LocalDateTime.now());
            
            try {
                int year = request.getStartDate().getYear();
                leaveBalanceService.updateBalanceAfterLeaveApproval(
                    request.getUserId(), year, request.getTotalDays());
            } catch (Exception e) {
                System.err.println("Could not update leave balance: " + e.getMessage());
            }
        }
        
        return leaveRequestRepository.save(request);
    }
    
    public void deleteLeaveRequest(Long id) {
        LeaveRequest request = getLeaveRequestById(id);
        if (!"PENDING".equals(request.getStatus())) {
            throw new IllegalStateException("Can only delete pending requests");
        }
        leaveRequestRepository.delete(request);
    }
}
