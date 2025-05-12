# Vital Essence Project Documentation

## 1. Overview

**Vital Essence** is a web application designed to manage user authentication and related functionalities. It consists of two main components:

- **Frontend**: A lightweight interface built with HTML, CSS, and vanilla JavaScript, providing a user-friendly experience for interacting with backend services.

- **Backend**: A Spring Boot application implementing JWT (JSON Web Token) authentication, currently supporting user creation, with planned features for email-related operations and password management.

The backend uses a PostgreSQL database for persistent storage, and the authentication system leverages Spring Security with JWT for secure, stateless session management. The project is in its early stages, with a single user creation endpoint, and will expand to include email notifications and password reset functionality.

### Objectives
- Provide a secure and scalable authentication system using JWT.
- Enable user management with a simple, intuitive frontend.
- Lay the foundation for email-based features (e.g., notifications, password recovery).
- Ensure maintainability and extensibility for future enhancements.

## 2. Project Setup

### Prerequisites
- **Frontend**:
  - A modern web browser (e.g., Chrome, Firefox).
  - A code editor (e.g., VS Code).
  - (Optional) A local web server (e.g., Live Server extension for VS Code) for development.
- **Backend**:
  - Java 17 or later.
  - Maven or Gradle for dependency management.
  - PostgreSQL 13 or later.
  - An IDE (e.g., IntelliJ IDEA, Eclipse).
- **General**:
  - Git for version control.
  - Docker (optional) for containerized database setup.

### Installation

#### Frontend
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd web

Open the index.html file in a browser or use a local development server:

# Using VS Code Live Server
code . && live-server

The frontend files (index.html, styles.css, script.js) are located in the frontend directory. No build step is required, as it uses vanilla HTML, CSS, and JavaScript.

Backend

Navigate to the backend directory:

cd server

Set up PostgreSQL:

Install PostgreSQL locally or use Docker:

docker run -d --name vital-essence-db -p 6000:5432 -e POSTGRES_USER=albqvxc -e POSTGRES_PASSWORD=docker -e POSTGRES_DB=vital-essence postgres

Ensure the database vital-essence is created and accessible at localhost:6000.

Configure the application properties:

Security Note: Replace jwt.secret with a cryptographically secure key in production and use environment variables for sensitive data.

Build and run the application:

mvn clean install
mvn spring-boot:run

The backend will start on http://localhost:8080.

3. Dependencies

**`Backend Dependencies`**

The backend uses Maven for dependency management. Key dependencies in pom.xml include:

**`Frontend Dependencies`**

The frontend uses vanilla HTML, CSS, and JavaScript with no external dependencies.

(Planned) Forms for login, password reset, and email notifications.

Design Principles:

Minimalist and responsive design.

No external frameworks to keep it lightweight.

Asynchronous requests to the backend for a smooth user experience.

Backend

Technologies: Spring Boot 3.x, Spring Security 6.x, PostgreSQL, JWT.

Purpose: Handles authentication, user management, and (planned) email and password functionalities.

Key Components:

AuthController: Handles authentication-related requests, such as user creation.

Database: PostgreSQL stores user data, with credentials configured in application.properties.

Authentication Flow

User Registration:

The frontend sends a POST request to /auth/register with user details (e.g., username, password).

The backend creates the user in the database and returns a success response.

User Login (Planned):

The frontend sends a POST request to /auth/login with credentials.

The backend authenticates using AuthenticationManager, generates a JWT via JwtUtil, and returns it.

Protected Requests:

The frontend includes the JWT in the Authorization header (Bearer <token>).

JwtRequestFilter validates the token, sets the authentication context, and allows access to protected endpoints.

Email and Password Features (Planned):

Email notifications for account actions (e.g., registration confirmation).

Password reset via email with a secure token.

**5. API Specification**

Current Endpoints

POST /auth/register

Description: Creates a new user in the system.

Request:

Method: POST

URL: /auth/register

Headers: Content-Type: application/json

Body:

{
  "username": "string",
  "password": "string",
  "email": "string"
}

**Constraints:**

username: Unique, 3-50 characters.

password: Minimum 8 characters, must include letters and numbers.

email: Valid email format.

Response:

Success (201 Created):

{
  "message": "User created successfully"
}

Error (400 Bad Request):

{
  "error": "Invalid input data"
}

Error (409 Conflict):

{
  "error": "Username or email already exists"
}

Planned Endpoints

POST /auth/login:

Authenticates a user and returns a JWT.

Request: { "username": "string", "password": "string" }

Response: { "token": "string" }

**POST /auth/forgot-password:**

Initiates a password reset process by sending a reset link via email.

Request: { "email": "string" }

Response: { "message": "Reset link sent" }

**POST /auth/reset-password:**

Resets the user’s password using a token sent via email.

Request: { "token": "string", "newPassword": "string" }

Response: { "message": "Password reset successfully" }

**POST /auth/send-notification:**

Sends a notification email to the user (e.g., account confirmation).

Request: { "email": "string", "message": "string" }



Response: { "message": "Notification sent" }

SecurityConfig: Configures Spring Security with JWT authentication:

JwtRequestFilter: Validates JWT tokens and sets the security context.

JwtUtil: Handles JWT operations (not shown but assumed to include token generation, validation, and username extraction).

Configure email properties in application.properties:

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-specific-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

Note: Use an app-specific password for Gmail or a similar secure credential for other providers.

Create an email service:

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("your-email@gmail.com");
        mailSender.send(message);
    }
}

Usage

Registration Confirmation: Send an email after successful registration.

Password Reset: Generate a secure token, store it in the database with an expiration, and send a reset link.

8. Testing

Backend Testing

Tools: JUnit 5, Mockito, Spring Boot Test, Spring Security Test.

Setup:

Ensure test dependencies are included (see Dependencies section).

Create test classes in src/test/java.

Example Test (for AuthController):

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void testRegisterSuccess() throws Exception {
        UserDTO userDTO = new UserDTO("testuser", "password123", "test@example.com");
        doNothing().when(userService).createUser(any(UserDTO.class));

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"password123\",\"email\":\"test@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User created successfully"));
    }
}

Running Tests:

mvn test

Frontend Testing

Tools: Jest with jest-dom for unit testing DOM interactions.

Setup:

Install Node.js and npm.

Initialize a Node project in the frontend directory:

Running Tests:

npm test

9. Deployment

Frontend Deployment





Option: Host static files using Nginx or a static hosting service (e.g., Netlify, Vercel).



Steps with Nginx:





Install Nginx:

sudo apt update
sudo apt install nginx



Copy frontend files to Nginx’s web directory:

sudo cp -r frontend/* /var/www/html/



Configure Nginx (/etc/nginx/sites-available/default):

server {
    listen 80;
    server_name your-domain.com;
    root /var/www/html;
    index index.html;
}



Restart Nginx:

sudo systemctl restart nginx

Note: Use HTTPS by configuring SSL with Let’s Encrypt.

10. Development Workflow

Git Workflow

Branching:

main: Production-ready code.

develop: Integration branch for features.

Commit Messages:

Use descriptive messages: Add user registration endpoint, Fix CORS configuration.

Pull Requests:

Create PRs from feature branches to develop.

11. Security Considerations

JWT Security:

Use a strong, randomly generated jwt.secret (at least 256 bits) in production.

Ensure JwtUtil validates token signatures, expiration, and claims.

Implement token refresh mechanisms for long-lived sessions.

**Password Storage:**

Passwords are hashed using BCryptPasswordEncoder, which is secure.

**Database Credentials:**

Move sensitive data (spring.datasource.username, spring.datasource.password) to environment variables or a secrets manager.

**Input Validation:**

Validate all user inputs (e.g., username, email) on both frontend and backend to prevent injection attacks.

Password Reset:

Implement a secure token-based password reset flow with expiration.

### Database Connection Issues:

Check PostgreSQL is running on localhost:6000 with correct credentials.

Use tools like psql to test connectivity:

psql -h localhost -p 6000 -U **`<your_user>`** -d **`<your_db>`**

Verify the JDBC URL and driver in application.properties.