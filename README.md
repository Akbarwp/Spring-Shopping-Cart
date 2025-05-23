## Installation

Follow the steps below to clone and run the project in your local environment:

1. Clone repository:

    ```bash
    git clone https://github.com/Akbarwp/Spring-Shopping-Cart.git
    ```

2. Setup application properties in the `application.properties` file:

    ```plaintext
    spring.application.name=Spring Boot Shopping Cart
    server.port=8080
    server.servlet.context-path=/
    spring.jackson.time-zone=Asia/Jakarta
    
    logging.file.name=logs/application.log
    logging.file.path=logs/
    
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=
    spring.datasource.url=jdbc:mysql://localhost:3306/spring_shopping_cart
    spring.datasource.type=com.zaxxer.hikari.HikariDataSource
    spring.datasource.hikari.minimum-idle=10
    spring.datasource.hikari.maximum-pool-size=50
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.properties.hibernate.show_sql=true
    
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    spring.jpa.hibernate.ddl-auto=create
    
    api.prefix=/api/v1
    
    spring.servlet.multipart.max-file-size=5MB
    spring.servlet.multipart.max-request-size=5MB
    
    auth.token.expirationInMils={time in milisecond, 3600000 = 1 hour}
    auth.token.jwtSecret={generate jwt token base64}
    ```
