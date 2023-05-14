# TravellerOnline

Final Project

IT Talents Season 15

TravellerOnline is a free web service designed to connect travel enthusiasts and enable them to share their experiences, post comments and interact with other users.

ℹ️ TravellerOnline is currently in development mode, and the database is local. As a result, the web service is not accessible at the moment. Existing bugs are being addressed during this phase. The decision to implement substantial new features is currently under consideration.


## Technology Stack

### Languages & Frameworks:
- Java 17, Spring Boot, Spring MVC, Spring Data, Hibernate, Spring Validation, Spring Security
### Database & Persistence:
- MySQL, MySQL server, Workbench, JDBC API, MySQL driver
### Build & Dependency Management:
- Maven
### Testing & Documentation:
- JUnit 5, Swagger (OpenAPI), Postman
### Utilities & Libraries:
- Lombok, Model Mapper, BCrypt, Java Mail Sender, Log4j
### Development Environment:
- IntelliJ IDEA, Git, GitHub
### Other:
- Tomcat, ChatGPT

## Testing the API with Postman
- Clone repository
```
git clone https://github.com/rodopiip/TravellerOnline.git
```
- Open IntelliJ project
- Build project with Maven
- [Install TODO settings](https://github.com/rodopiip/TravellerOnline/tree/master/IDE_TODOSettings)
- Reverse-engeneer database by applying
 ```TravellerOnline\DataBase\ddl.sql```
- Configure server port (Default: 3333)
- Run the spring boot application from *TravellerOnlineApplication.java*
- [Send requests via Postman](https://documenter.getpostman.com/view/26793882/2s93XsYRxe#b23de5e5-755e-45fa-9b4d-0dea998a74f6)

## Planned Features

1. Implement Flyway for database migration management
2. Develop comprehensive unit tests
3. Incorporate ethically-focused features

## Additional Info
- [Work notes](https://docs.google.com/document/d/18VVJxBbhBrgDCXVcrU1pEjvy6LdouT1P7scFiyf5ZoM/edit?usp=sharing)
- [Action map](https://docs.google.com/spreadsheets/d/1Db2u_mNmnjyZQibEdSYKLZWHbPTS7BNLUycky8Pz15Q/edit?usp=sharing)
- [Miro brainstorm board](https://miro.com/app/board/uXjVMW0nLjE=/)

## Contributers
@MariyaRadeva @PeterRachev
