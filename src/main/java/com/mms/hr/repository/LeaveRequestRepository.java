package com.mms.hr.repository;

import com.mms.hr.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    
    List<LeaveRequest> findByUserId(Long userId);
    
    List<LeaveRequest> findByStatus(String status);
    
    @Query("SELECT lr FROM LeaveRequest lr WHERE lr.userId = :userId ORDER BY lr.requestDate DESC")
    List<LeaveRequest> findByUserIdOrderByRequestDateDesc(@Param("userId") Long userId);
    
    @Query("SELECT lr FROM LeaveRequest lr ORDER BY lr.requestDate DESC")
    List<LeaveRequest> findAllOrderByRequestDateDesc();
}
