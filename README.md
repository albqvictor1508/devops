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
   cd vital-essence/frontend





Open the index.html file in a browser or use a local development server:

# Using VS Code Live Server
code . && live-server



The frontend files (index.html, styles.css, script.js) are located in the frontend directory. No build step is required, as it uses vanilla HTML, CSS, and JavaScript.

Backend





Navigate to the backend directory:

cd vital-essence/backend



Set up PostgreSQL:





Install PostgreSQL locally or use Docker:

docker run -d --name vital-essence-db -p 6000:5432 -e POSTGRES_USER=albqvxc -e POSTGRES_PASSWORD=docker -e POSTGRES_DB=vital-essence postgres



Ensure the database vital-essence is created and accessible at localhost:6000.



Configure the application properties:





Edit src/main/resources/application.properties:

spring.application.name=validation
spring.datasource.username=albqvxc
spring.datasource.password=docker
spring.datasource.url=jdbc:postgresql://localhost:6000/vital-essence
jwt.secret=devops-project
jwt.expiration=86400000
jwt.expiration.remember=604800000



Security Note: Replace jwt.secret with a cryptographically secure key in production and use environment variables for sensitive data.



Build and run the application:

mvn clean install
mvn spring-boot:run

The backend will start on http://localhost:8080.

Project Structure

vital-essence/
├── frontend/
│   ├── index.html        # Main HTML file
│   ├── styles.css        # CSS styles
│   └── script.js         # JavaScript for frontend logic
├── backend/
│   ├── src/main/java/com/vital_essence/validation/
│   │   ├── controller/   # REST controllers (e.g., AuthController)
│   │   ├── security/     # Security configurations (e.g., SecurityConfig, JwtRequestFilter)
│   │   ├── util/         # Utility classes (e.g., JwtUtil)
│   │   └── ValidationApplication.java  # Main Spring Boot application
│   └── src/main/resources/
│       └── application.properties  # Configuration file
├── README.md             # Project overview
└── pom.xml               # Maven dependencies (backend)

3. Dependencies

Backend Dependencies

The backend uses Maven for dependency management. Key dependencies in pom.xml include:

<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <!-- JWT Library -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
    </dependency>
    <!-- Lombok for boilerplate reduction -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>
    <!-- Spring Boot Starter Test (for testing) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!-- Spring Security Test -->
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

Frontend Dependencies

The frontend uses vanilla HTML, CSS, and JavaScript with no external dependencies. Optional tools for development:





Normalize.css: For consistent CSS rendering across browsers.

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">



Axios: For simplified HTTP requests (optional, if fetch is insufficient).

<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js"></script>

4. Architecture

Frontend





Technologies: HTML5, CSS3, vanilla JavaScript.



Purpose: Provides a simple interface for user interactions, such as signing up. It communicates with the backend via HTTP requests (using fetch API).



Key Features:





Form for user registration.



(Planned) Forms for login, password reset, and email notifications.



Design Principles:





Minimalist and responsive design.



No external frameworks to keep it lightweight.



Asynchronous requests to the backend for a smooth user experience.

Backend





Technologies: Spring Boot 3.x, Spring Security 6.x, PostgreSQL, JWT.



Purpose: Handles authentication, user management, and (planned) email and password functionalities.



Key Components:





SecurityConfig: Configures Spring Security with stateless JWT authentication, disabling CSRF and enabling public access to /auth/** endpoints.



JwtRequestFilter: Validates JWT tokens in the Authorization header and sets the security context.



JwtUtil: Utility class for JWT operations (e.g., token generation, validation, username extraction).



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

5. API Specification

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



Constraints:





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



POST /auth/forgot-password:





Initiates a password reset process by sending a reset link via email.



Request: { "email": "string" }



Response: { "message": "Reset link sent" }



POST /auth/reset-password:





Resets the user’s password using a token sent via email.



Request: { "token": "string", "newPassword": "string" }



Response: { "message": "Password reset successfully" }



POST /auth/send-notification:





Sends a notification email to the user (e.g., account confirmation).



Request: { "email": "string", "message": "string" }



Response: { "message": "Notification sent" }

6. Implementation Details

Frontend





index.html: Contains a form for user registration, styled with styles.css.



script.js: Handles form submission, sending a fetch request to /auth/register.



styles.css: Ensures a responsive, user-friendly design.

Example Frontend Code

Below is a complete example of the frontend files to illustrate the registration functionality.

index.html:

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vital Essence - Register</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Vital Essence</h1>
        <form id="registerForm">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Register</button>
        </form>
    </div>
    <script src="script.js"></script>
</body>
</html>

styles.css:

body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    background: white;
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
}

h1 {
    text-align: center;
    color: #333;
}

.form-group {
    margin-bottom: 1rem;
}

label {
    display: block;
    margin-bottom: 0.5rem;
    color: #555;
}

input {
    width: 100%;
    padding: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 1rem;
}

button {
    width: 100%;
    padding: 0.75rem;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
}

button:hover {
    background-color: #218838;
}

script.js:

document.getElementById('registerForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;

    try {
        const response = await fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password, email })
        });
        const data = await response.json();
        if (response.ok) {
            alert(data.message);
            document.getElementById('registerForm').reset();
        } else {
            alert(`Error: ${data.error}`);
        }
    } catch (error) {
        alert('Network error: ' + error.message);
    }
});

Backend





SecurityConfig: Configures Spring Security with JWT authentication:

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}



JwtRequestFilter: Validates JWT tokens and sets the security context.



JwtUtil: Handles JWT operations (not shown but assumed to include token generation, validation, and username extraction).



AuthController: Manages authentication endpoints (example for /auth/register):

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("User created successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDTO(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO("Username or email already exists"));
        }
    }
}

Database





Schema: A users table with columns for id (primary key), username, password (hashed with BCrypt), email, and created_at.



Example SQL:

CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

7. Email Integration (Planned)

Overview

The project plans to implement email-related features, such as registration confirmations and password reset links. This will use Spring Boot’s JavaMailSender to send emails via an SMTP server (e.g., Gmail, SendGrid).

Configuration





Add the Spring Boot Mail dependency:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>



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

cd frontend
npm init -y
npm install --save-dev jest @testing-library/dom @testing-library/jest-dom



Add to package.json:

"scripts": {
    "test": "jest"
}



Example Test (for script.js):

/**
 * @jest-environment jsdom
 */
import '@testing-library/jest-dom';

describe('Register Form', () => {
    test('submits form data correctly', async () => {
        document.body.innerHTML = `
            <form id="registerForm">
                <input id="username" value="testuser">
                <input id="email" value="test@example.com">
                <input id="password" value="password123">
                <button type="submit">Register</button>
            </form>
        `;
        global.fetch = jest.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({ message: 'User created successfully' })
            })
        );

        require('./script.js');

        const form = document.getElementById('registerForm');
        form.dispatchEvent(new Event('submit'));

        await new Promise(process.nextTick);
        expect(fetch).toHaveBeenCalledWith(
            'http://localhost:8080/auth/register',
            expect.objectContaining({
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username: 'testuser', password: 'password123', email: 'test@example.com' })
            })
        );
    });
});



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

Backend Deployment





Option: Deploy to a Platform-as-a-Service (PaaS) like Heroku or a VPS with a Java runtime.



Steps with Heroku:





Install Heroku CLI and login:

heroku login



Create a Heroku app:

cd backend
heroku create vital-essence-backend



Add PostgreSQL add-on:

heroku addons:create heroku-postgresql:hobby-dev



Configure environment variables:

heroku config:set SPRING_DATASOURCE_URL=$(heroku config:get DATABASE_URL)
heroku config:set SPRING_DATASOURCE_USERNAME=albqvxc
heroku config:set SPRING_DATASOURCE_PASSWORD=docker
heroku config:set JWT_SECRET=your-secure-secret



Deploy the application:

git push heroku main



Note: Update the frontend to use the deployed backend URL (e.g., https://vital-essence-backend.herokuapp.com).

10. Development Workflow

Git Workflow





Branching:





main: Production-ready code.



develop: Integration branch for features.



Feature branches: feature/<feature-name> (e.g., feature/login-endpoint).



Commit Messages:





Use descriptive messages: Add user registration endpoint, Fix CORS configuration.



Pull Requests:





Create PRs from feature branches to develop.



Include a description of changes and link to any issues.



Require at least one reviewer approval before merging.



Commands:

git checkout -b feature/<feature-name>
git commit -m "Description of changes"
git push origin feature/<feature-name>
# Create PR on GitHub/GitLab

Coding Standards





Backend:





Follow Java naming conventions (e.g., camelCase for variables, UpperCamelCase for classes).



Use @RestController and @Service annotations appropriately.



Validate inputs with @Valid and handle exceptions with @ExceptionHandler.



Frontend:





Use semantic HTML5 tags.



Keep CSS modular and scoped to components.



Write modular JavaScript with clear function names.

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