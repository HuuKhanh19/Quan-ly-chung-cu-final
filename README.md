# 🏢 Hệ thống Quản lý Chung cư - Backend

Hệ thống quản lý chung cư được xây dựng với Spring Boot, hỗ trợ quản lý cư dân, thu phí và thống kê.

---

## 🚀 Hướng dẫn chạy nhanh

### 1. Yêu cầu

- **Java**: JDK 17 trở lên
- **MySQL**: 8.0+
- **Maven**: 3.8+
- **IDE**: IntelliJ IDEA / Eclipse / VS Code

### 2. Cài đặt Database

**Bước 1: Tạo database**
```sql
CREATE DATABASE QuanLyChungCu 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

**Bước 2: Import schema và data**
```bash
# Import cấu trúc bảng
mysql -u root -p QuanLyChungCu < database/schema.sql

# Import dữ liệu mẫu
mysql -u root -p QuanLyChungCu < database/insert_data_final.sql
```

### 3. Cấu hình Backend

**Mở file** `src/main/resources/application.properties` và sửa:

```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/QuanLyChungCu
spring.datasource.username=root
spring.datasource.password=your_password_here

# JWT Secret Key (tối thiểu 32 ký tự)
jwt.signerKey=your_secret_key_minimum_32_characters_long_here
```

### 4. Chạy Backend

**Cách 1: Dùng Maven**
```bash
mvn clean install
mvn spring-boot:run
```

**Cách 2: Dùng IDE**
- Mở project bằng IntelliJ IDEA
- Chạy file `QuanLyChungCuApplication.java`

Backend sẽ chạy tại: **http://localhost:8080**

### 5. Chạy Frontend

**Clone và cài đặt:**
```bash
git clone https://github.com/Dinhthuy2k5/QL-Chung-cu.git
cd QL-Chung-cu
npm install
npm start
```

Frontend sẽ chạy tại: **http://localhost:3000**

---

## 🔑 Tài khoản mặc định

| Vai trò | Username | Password | Quyền hạn       |
|---------|----------|----------|-----------------|
| Admin | `admin` | `admin` | Quản lý user    |
| Quản lý | `quanly` | `quanly` | Quản lý cư dân  |
| Kế toán | `ketoan` | `ketoan` | Quản lý thu phí |

⚠️ **Lưu ý**: Đổi password sau lần đăng nhập đầu tiên!

---

## 📁 Cấu trúc thư mục

```
quan-ly-chung-cu-backend/
├── src/main/java/com/huukhanh19/quan_ly_chung_cu/
│   ├── controller/       # REST API endpoints
│   ├── service/          # Business logic
│   ├── repository/       # Database access
│   ├── entity/           # JPA entities
│   ├── dto/              # Request/Response objects
│   ├── mapper/           # Object mapping
│   ├── configuration/    # Spring configuration
│   └── exception/        # Exception handling
├── src/main/resources/
│   └── application.properties
└── database/
    ├── schema.sql        # Cấu trúc bảng
    └── insert_data_final.sql  # Dữ liệu mẫu
```

---

## 🧪 Test API

### Dùng Postman

**Bước 1: Đăng nhập**
```http
POST http://localhost:8080/auth/token
Content-Type: application/json

{
    "username": "quanly",
    "password": "quanly"
}
```

**Response:**
```json
{
    "code": 1000,
    "result": {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
        "authenticated": true
    }
}
```

**Bước 2: Gọi API với token**
```http
GET http://localhost:8080/can-ho
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---