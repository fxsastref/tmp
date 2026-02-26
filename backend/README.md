# Platinum Backend Documentation
The `/backend` folder contains the entirety of **Platinum's** logic and database management code. The codebase for the backend is developed using **JDK 21**, **Springboot 4.0.1** and **Maven 3.9.12**.

The main logic of this part of the application is to fetch data from the external **Steam API**, save and load data from the local instanciated **PostgreSQL Database** and the management of models and data structures.

# Installation and Setup

1. Have a runing instance of a **PostgreSQL 17** or higher with a usable database named `platinum` up & running.

2. Ensure you have **Java Development Kit 21** installed in your system.

3. Check that you have **Maven 3.9.12** or higher installed.

4. Create `application-secrets.properties`:

> The route for `application-secrets.properties` is: `backend\src\main\resources\application-secrets.properties`.

**application-secrets.properties contents**
```properties
steam.api-key=FEB3BB4335FE60E6DB0109FC1174A006
steam.api-url=https://api.steampowered.com
steam.store-url=https://store.steampowered.com
```

5. Execute a clean install for the proyect:

```bash
# Go to /backend folder
cd backend

# Execute clean install
mvn install
```

# Start Development Envirorment

Start the Springboot proyect using Maven:
```bash
mvn spring-boot:run
```

>[!NOTE]
> It is important to run the `mvn` command inside the `/backend` folder.

# Swagger

The proyect accounts for a **swagger** documentation page at `http://localhost:8080/swagger-ui/index.html#/` that you can access after starting the server.

# Techinal Documentation

To see the details of the structure, access [README_backend_structure.md](README_backend_structure.md)