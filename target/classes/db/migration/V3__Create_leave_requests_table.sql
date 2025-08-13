CREATE TABLE leave_requests (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    request_type VARCHAR(50) NOT NULL,
    reason VARCHAR(50) NOT NULL,
    detail_reason TEXT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    partial_day VARCHAR(20) DEFAULT 'FULL_DAY',
    total_days INTEGER NOT NULL,
    approver_id BIGINT,
    supervisor_id BIGINT,
    inform_to VARCHAR(500),
    expected_approve DATE,
    request_date DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    approved_at TIMESTAMP,
    approved_by BIGINT,
    rejection_reason TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_leave_requests_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_leave_requests_approver FOREIGN KEY (approver_id) REFERENCES users(id),
    CONSTRAINT fk_leave_requests_supervisor FOREIGN KEY (supervisor_id) REFERENCES users(id),
    CONSTRAINT fk_leave_requests_approved_by FOREIGN KEY (approved_by) REFERENCES users(id),
    CONSTRAINT chk_leave_requests_end_after_start CHECK (end_date >= start_date),
    CONSTRAINT chk_leave_requests_total_days_positive CHECK (total_days > 0),
    CONSTRAINT chk_leave_requests_status CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED', 'CANCELLED'))
);

CREATE INDEX idx_leave_requests_user_id ON leave_requests(user_id);
CREATE INDEX idx_leave_requests_status ON leave_requests(status);
CREATE INDEX idx_leave_requests_request_date ON leave_requests(request_date);
CREATE INDEX idx_leave_requests_start_date ON leave_requests(start_date);
CREATE INDEX idx_leave_requests_approver_id ON leave_requests(approver_id);
