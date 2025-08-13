# MMS HR Service Backend

Spring Boot backend for Leave Request Management System

## 🚀 Quick Start

### Prerequisites (Auto-installed by setup script)
- ✅ Java 17+ (OpenJDK)
- ✅ Maven 3.8+
- ✅ PostgreSQL 15+
- ✅ Homebrew (macOS package manager)

### Database Setup (Already completed)
- ✅ Database `mms_hr_service` created
- ✅ User `mms_hr` with password `password123` created
- ✅ Permissions granted

### Running the Application

1. **Run database migrations:**
```bash
mvn flyway:migrate
```

2. **Start the application:**
```bash
mvn spring-boot:run
```

3. **Verify it's working:**
```bash
curl http://localhost:8080/api/users
curl http://localhost:8080/api/leave-types
```

## 📋 API Endpoints

### Leave Management
- `GET /api/leave-types` - Get available leave types
- `GET /api/leave-reasons` - Get leave reasons  
- `GET /api/leave-requests` - Get all leave requests
- `GET /api/leave-requests/user/{userId}` - Get requests by user
- `POST /api/leave-requests` - Create new leave request
- `PATCH /api/leave-requests/{id}/status` - Update request status

### User Management  
- `GET /api/users` - Get all active users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/approvers` - Get potential approvers

### Leave Balance
- `GET /api/leave-balance/{userId}` - Get user's leave balance

## 🌐 Integration

- **Backend URL:** http://localhost:8080
- **Frontend URL:** http://localhost:3000 (CORS enabled)
- **Database:** PostgreSQL on localhost:5432

## 🛠 Development

### Project Structure
```
src/main/java/com/mms/hr/
├── MmsHrBackendApplication.java     # Main application
├── config/CorsConfig.java           # CORS configuration
├── controller/                      # REST controllers
│   ├── LeaveRequestController.java
│   ├── UserController.java
│   └── LeaveBalanceController.java
├── entity/                          # JPA entities
│   ├── User.java
│   ├── LeaveBalance.java
│   └── LeaveRequest.java
├── repository/                      # Data repositories
│   ├── UserRepository.java
│   ├── LeaveBalanceRepository.java
│   └── LeaveRequestRepository.java
└── service/                         # Business logic
    ├── UserService.java
    ├── LeaveBalanceService.java
    └── LeaveRequestService.java
```

### Sample Data
The application includes sample data:
- 6 users with different roles (Manager, Supervisor, Director, Employees)
- Leave balances for all users for 2024
- Sample leave requests in different states

### Flyway Migrations
Database schema is managed via Flyway migrations in `src/main/resources/db/migration/`:
- V1: Create users table
- V2: Create leave_balances table
- V3: Create leave_requests table
- V4: Insert sample data

## 🔧 Available Commands

```bash
# Development
mvn spring-boot:run              # Start application
mvn spring-boot:run -Ddebug      # Start with debug mode

# Database
mvn flyway:info                  # Check migration status
mvn flyway:migrate              # Run pending migrations
mvn flyway:clean                # Clean database (DEV ONLY!)

# Testing
mvn test                        # Run tests
mvn clean package              # Build JAR file

# Verification
curl http://localhost:8080/api/users          # Test users endpoint
curl http://localhost:8080/api/leave-types    # Test leave types
curl http://localhost:8080/api/leave-balance/1 # Test leave balance
```

## 🎯 Next Steps

1. **Start the React frontend** at http://localhost:3000
2. **Test the integration** between frontend and backend
3. **Create leave requests** via the UI
4. **Monitor logs** for debugging if needed

## 🐛 Troubleshooting

### Common Issues

**PostgreSQL not running:**
```bash
brew services start postgresql
# or
brew services start postgresql@15
```

**Port 8080 already in use:**
```bash
lsof -ti:8080 | xargs kill -9
```

**Database connection failed:**
```bash
psql -h localhost -U mms_hr -d mms_hr_service
# Should connect without errors
```

**Maven build failed:**
```bash
mvn clean compile
# Check for compilation errors
```

## 📞 Support

- Check application logs in the terminal
- Verify PostgreSQL is running: `brew services list | grep postgresql`
- Test database connection: `psql -h localhost -U mms_hr -d mms_hr_service`
- Restart application: `Ctrl+C` then `mvn spring-boot:run`

**Happy coding! 🎉**
