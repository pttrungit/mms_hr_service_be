package com.mms.hr.controller;

import com.mms.hr.entity.LeaveRequest;
import com.mms.hr.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LeaveRequestController {
    
    @Autowired
    private LeaveRequestService leaveRequestService;
    
    @GetMapping("/leave-types")
    public ResponseEntity<List<Map<String, String>>> getLeaveTypes() {
        List<Map<String, String>> leaveTypes = Arrays.asList(
            Map.of("value", "ANNUAL", "label", "Annual Leave"),
            Map.of("value", "SICK", "label", "Sick Leave"),
            Map.of("value", "MATERNITY", "label", "Maternity Leave"),
            Map.of("value", "PATERNITY", "label", "Paternity Leave"),
            Map.of("value", "EMERGENCY", "label", "Emergency Leave"),
            Map.of("value", "UNPAID", "label", "Unpaid Leave")
        );
        
        System.out.println("📋 Returning leave types: " + leaveTypes.size() + " types");
        return ResponseEntity.ok(leaveTypes);
    }
    
    @GetMapping("/leave-reasons")
    public ResponseEntity<List<Map<String, String>>> getLeaveReasons() {
        List<Map<String, String>> leaveReasons = Arrays.asList(
            Map.of("value", "VACATION", "label", "Vacation"),
            Map.of("value", "MEDICAL", "label", "Medical"),
            Map.of("value", "FAMILY", "label", "Family Emergency"),
            Map.of("value", "PERSONAL", "label", "Personal"),
            Map.of("value", "CONFERENCE", "label", "Conference/Training"),
            Map.of("value", "OTHER", "label", "Other")
        );
        
        System.out.println("📋 Returning leave reasons: " + leaveReasons.size() + " reasons");
        return ResponseEntity.ok(leaveReasons);
    }
    
    @GetMapping("/leave-requests")
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        List<LeaveRequest> requests = leaveRequestService.getAllLeaveRequests();
        System.out.println("📄 Returning " + requests.size() + " leave requests");
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/leave-requests/user/{userId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByUserId(@PathVariable Long userId) {
        List<LeaveRequest> requests = leaveRequestService.getLeaveRequestsByUserId(userId);
        System.out.println("📄 Returning " + requests.size() + " requests for user " + userId);
        return ResponseEntity.ok(requests);
    }
    
    @PostMapping("/leave-requests")
    public ResponseEntity<?> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        try {
            System.out.println("📝 Creating leave request: " + leaveRequest);
            LeaveRequest savedRequest = leaveRequestService.createLeaveRequest(leaveRequest);
            System.out.println("✅ Leave request created with ID: " + savedRequest.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
        } catch (Exception e) {
            System.err.println("❌ Error creating leave request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    @PatchMapping("/leave-requests/{id}/status")
    public ResponseEntity<?> updateLeaveRequestStatus(
            @PathVariable Long id, 
            @RequestBody Map<String, String> statusUpdate) {
        try {
            String status = statusUpdate.get("status");
            System.out.println("🔄 Updating request " + id + " status to: " + status);
            
            LeaveRequest updatedRequest = leaveRequestService.updateRequestStatus(id, status);
            System.out.println("✅ Request status updated successfully");
            return ResponseEntity.ok(updatedRequest);
        } catch (Exception e) {
            System.err.println("❌ Error updating request status: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
