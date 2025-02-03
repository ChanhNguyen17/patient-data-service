# **Patient Data Service** 🏥

A cloud-based **microservice** built with **Kotlin** and **Spring Boot** for handling periodic medical data of multiple patients. It **collects, stores, and provides a simple API** for accessing patient health data.

---

## 🚀 **Getting Started**

### **Prerequisites**
Ensure you have the following installed:
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)

### **Run the Service**

1️⃣ **Start the PostgreSQL database and dependencies using Docker Compose:**
```sh
docker compose up -d
```

2️⃣ **Run the microservice locally:**
```sh
./run-local.sh
```

---

## 🛠 **Code Formatting**

To maintain consistent code style, format your code using **ktlint**:
```sh
./gradlew ktlintFormat
```

---

## ✅ **Running Tests**

Execute unit and integration tests using:
```sh
./gradlew test
```

---

## 📄 **API Model Example: Patient**

```json
{
    "patient_id": "bd0e53dc-9c8c-4553-b50a-0def7c475c48",
    "name": "John Doe",
    "data": [
        {
            "data_id": "e110c5f5-9cc6-4a26-a3a0-839db9b44f0f",
            "blood_pressure": "105/89",
            "heart_rate": 86,
            "timestamp": "2025-02-02T17:32:17.214007Z"
        }
    ]
}
```

---

## 📖 **API Documentation**

The API documentation is available via **Swagger UI**:  
🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🚧 **Next Steps & Improvements**

Here are some **potential enhancements** for the project:

✅ **Security Enhancements:**
- Implement **authentication & authorization** (e.g., OAuth2, JWT).
- Restrict access so that **only authorized users** can view patient data.

✅ **Testing Improvements:**
- Expand test coverage to include **controller and repository layers**.
- Implement **end-to-end API tests** with tools like **Postman/Newman**.

✅ **Scalability & Performance:**
- Optimize the database schema for **faster queries**.
- Implement **caching mechanisms** (e.g., Redis) for frequently accessed data.

✅ **Observability & Monitoring:**
- Add **logging and tracing** (e.g., OpenTelemetry, ELK Stack).
- Implement **health checks and metrics** using **Spring Boot Actuator**.
