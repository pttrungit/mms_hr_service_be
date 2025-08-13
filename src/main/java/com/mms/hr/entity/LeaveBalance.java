package com.mms.hr.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "leave_balances")
@EntityListeners(AuditingEntityListener.class)
public class LeaveBalance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(name = "total_days", nullable = false)
    private Integer totalDays = 0;
    
    @Column(name = "used_days", nullable = false)
    private Integer usedDays = 0;
    
    @Column(name = "remaining_days", nullable = false)
    private Integer remainingDays = 0;
    
    @Column(name = "carry_forward_days")
    private Integer carryForwardDays = 0;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public LeaveBalance() {}
    
    public LeaveBalance(Long userId, Integer year, Integer totalDays) {
        this.userId = userId;
        this.year = year;
        this.totalDays = totalDays;
        this.remainingDays = totalDays;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    
    public Integer getTotalDays() { return totalDays; }
    public void setTotalDays(Integer totalDays) { this.totalDays = totalDays; }
    
    public Integer getUsedDays() { return usedDays; }
    public void setUsedDays(Integer usedDays) { this.usedDays = usedDays; }
    
    public Integer getRemainingDays() { return remainingDays; }
    public void setRemainingDays(Integer remainingDays) { this.remainingDays = remainingDays; }
    
    public Integer getCarryForwardDays() { return carryForwardDays; }
    public void setCarryForwardDays(Integer carryForwardDays) { this.carryForwardDays = carryForwardDays; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @Override
    public String toString() {
        return "LeaveBalance{" +
                "id=" + id +
                ", userId=" + userId +
                ", year=" + year +
                ", totalDays=" + totalDays +
                ", usedDays=" + usedDays +
                ", remainingDays=" + remainingDays +
                '}';
    }
}
