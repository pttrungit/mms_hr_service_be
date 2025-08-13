INSERT INTO users (name, email, role, department, is_active) VALUES
('John Manager', 'john@company.com', 'Manager', 'Human Resources', true),
('Sarah Supervisor', 'sarah@company.com', 'Supervisor', 'Engineering', true),
('Mike Director', 'mike@company.com', 'Director', 'Operations', true),
('Alice Employee', 'alice@company.com', 'Employee', 'Engineering', true),
('Bob Developer', 'bob@company.com', 'Employee', 'Engineering', true),
('Carol Analyst', 'carol@company.com', 'Employee', 'Business Analysis', true);

UPDATE users SET manager_id = 1 WHERE id IN (2, 6);
UPDATE users SET manager_id = 2 WHERE id IN (4, 5);
UPDATE users SET manager_id = 3 WHERE id = 1;

INSERT INTO leave_balances (user_id, year, total_days, used_days, remaining_days) VALUES
(1, 2024, 20, 5, 15),
(2, 2024, 20, 3, 17),
(3, 2024, 25, 8, 17),
(4, 2024, 20, 2, 18),
(5, 2024, 20, 0, 20),
(6, 2024, 20, 4, 16);

INSERT INTO leave_requests (
    user_id, request_type, reason, detail_reason, start_date, end_date, 
    total_days, approver_id, supervisor_id, status, request_date
) VALUES
(4, 'ANNUAL', 'VACATION', 'Family vacation in Da Lat', '2024-12-20', '2024-12-25', 6, 2, 1, 'PENDING', '2024-12-01'),
(5, 'SICK', 'MEDICAL', 'Medical checkup', '2024-12-15', '2024-12-15', 1, 2, 1, 'APPROVED', '2024-12-10'),
(6, 'ANNUAL', 'PERSONAL', 'Personal matters', '2024-12-18', '2024-12-19', 2, 1, 3, 'PENDING', '2024-12-05');
