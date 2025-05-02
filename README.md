# Microservice 3: ms-ordenes

Permitir a cualquier USUARIO realizar órdenes de productos, y a ADMIN o SUPERADMIN verlas.

## Tecnologías Utilizadas:
- Java 17
- Spring Boot 3.3.5
- Maven
- JUnit y Mockito
- Jacoco
- SonarCloud
- Eureka Server
- Spring Cloud Config Server
- HashiCorp Vault
- Feign Client (para comunicación con ms-productos)

## Endpoints:

#### Listar órdenes (solo admins)

```http
GET /ordenes/listar
Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJyb2wiOiJTVVBFUkFETUlOIiwiaWF0IjoxNzQ2MjA3ODU2LCJleHAiOjE3NDYyMDgwOTYsInN1YiI6InN1cGVyQHN1cGVyLmNvbSJ9.K70iPItBEHeXNNfM7v6wurYYlzpSIgRTLCFeZYeZ6wZsdZ0-mYg1wr-rIgVMjwJXdwq1Cq1mjyuS7yqn1OiuMw
```

#### Crear orden (solo usuarios)

```http
POST /ordenes/crear HTTP/1.1
Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJyb2wiOiJVU1VBUklPIiwiaWF0IjoxNzQ2MjA3OTgxLCJleHAiOjE3NDYyMDgyMjEsInN1YiI6IkxpemE1NEB5YWhvby5jb20ifQ.ZUQpQ4GrC5ece0ii9qAM1mMYIUC5OTTzR2aKq6eKMUgE_85Tya0Cnfbkx2MxID63bvQUIhczl7qfhm0CDRoWdQ
```

| Parameter | Type    | Description                         |
|:----------|:--------|:------------------------------------|
| `productosIds`  | `List<Long>` |                               |

## Links - Proyecto Completo:

[<a href="https://github.com/Alexiz0r0/ms-auth-g9"><img src="https://img.shields.io/badge/ms%20auth-1b1f23?style=for-the-badge&logo=springboot&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-productos-g9"><img src="https://img.shields.io/badge/ms%20productos-1b1f23?style=for-the-badge&logo=springboot&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-ordenes-g9"><img src="https://img.shields.io/badge/ms%20ordenes-1b1f23?style=for-the-badge&logo=springboot&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-eureka-server-g9"><img src="https://img.shields.io/badge/ms%20eureka%20server-1b1f23?style=for-the-badge&logo=spring&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-config-server-g9"><img src="https://img.shields.io/badge/ms%20config%20server-1b1f23?style=for-the-badge&logo=spring&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-config-files-g9"><img src="https://img.shields.io/badge/ms%20config%20files-fe603b?style=for-the-badge&logo=files&logoColor=%23ffffff&labelColor=%23181717" ></a>](#)
