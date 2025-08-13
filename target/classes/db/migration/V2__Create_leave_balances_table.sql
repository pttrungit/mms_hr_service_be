CREATE TABLE leave_balances (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    year INTEGER NOT NULL,
    total_days INTEGER NOT NULL DEFAULT 0,
    used_days INTEGER NOT NULL DEFAULT 0,
    remaining_days INTEGER NOT NULL DEFAULT 0,
    carry_forward_days INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_leave_balances_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT uk_leave_balances_user_year UNIQUE (user_id, year),
    CONSTRAINT chk_leave_balances_total_positive CHECK (total_days >= 0),
    CONSTRAINT chk_leave_balances_used_positive CHECK (used_days >= 0),
    CONSTRAINT chk_leave_balances_remaining_positive CHECK (remaining_days >= 0)
);

CREATE INDEX idx_leave_balances_user_id ON leave_balances(user_id);
CREATE INDEX idx_leave_balances_year ON leave_balances(year);
