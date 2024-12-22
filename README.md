# **How to Run the Blog API Locally**

This guide explains how to set up and run the Blog API microservice on your local machine.

---

### **Prerequisites**

Before you start, ensure the following are installed on your local system:

1. **Java** (Version 17 or higher)
2. **Maven** (Version 3.8 or higher)
3. **Docker** (Version 20.x or higher)
4. **Git** (Version 2.x or higher)

---

### **Setup Instructions**

#### **1. Clone the Repository**
```bash
git clone git@github.com:cerliofaccojr/blog.git
cd blog
```

---

#### **2. Configure the Database**

1. **Run PostgreSQL with Docker Compose**:
   Start the PostgreSQL container:
   ```bash
   docker-compose up -d
   ```

2. **Verify Connection**:
   Use a PostgreSQL client or `psql` to connect to the database and ensure it’s set up:
   ```bash
   psql -U bloguser -d blogdb -h localhost
   ```

---

#### **3. Configure the Application**

1. **Set Active Profile**:
   Export the Spring profile for local development:
   ```bash
   export SPRING_PROFILES_ACTIVE=local
   ```

---

#### **4. Run the Application**

Use Maven to build and run the application:

```bash
./mvnw spring-boot:run
```

---

#### **5. Access the API**

Once the application is running, you can access it at:

- **Base URL**: `http://localhost:8080`

#### **Available API Endpoints**:
| Method | Endpoint                   | Description                            |
|--------|----------------------------|----------------------------------------|
| `GET`  | `/api/posts`               | List all blog posts.                   |
| `POST` | `/api/posts`               | Create a new blog post.                |
| `GET`  | `/api/posts/{id}`          | Retrieve a blog post by ID.            |
| `POST` | `/api/posts/{id}/comments` | Add a comment to a specific blog post. |

---

### **Flyway Database Migration**

The database schema is managed using Flyway. Upon startup, Flyway will:
1. Check the `src/main/resources/db/migration/` directory for migration scripts.
2. Apply pending migrations to the database.

**Initial Migration Script**: `V1__Initial_Schema.sql`

---

### **Testing the Application**

#### **1. Run Unit Tests**
Use Maven to execute unit tests:
```bash
./mvnw test
```

#### **2. Test API Endpoints**
You can test the API using tools like **Postman**, **Insomnia**, or **cURL**.

**Example Using `cURL`**:
```bash
# Create a new blog post
curl -X POST -H "Content-Type: application/json" -d '{"title": "My First Post", "content": "This is my first blog post."}' http://localhost:8080/api/posts

# List all blog posts
curl -X GET http://localhost:8080/api/posts
```

---

### **Troubleshooting**

#### **Common Issues**:

1. **Cannot Connect to the Database**:
   - Verify PostgreSQL is running and the credentials in `application-local.properties` are correct.

2. **Flyway Migration Errors**:
   - Ensure the database schema is empty before running migrations.
   - Check the migration script syntax for errors.

3. **Application Fails to Start**:
   - Ensure the `SPRING_PROFILES_ACTIVE` environment variable is set to `local`:
     ```bash
     export SPRING_PROFILES_ACTIVE=local
     ```

---
