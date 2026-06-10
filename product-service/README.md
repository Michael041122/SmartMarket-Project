# ms-pedidos

Microservicio de pedidos con Spring Boot, JPA, MySQL y registro en Eureka Server.

## Endpoints

- `GET /api/pedidos`
- `GET /api/pedidos/{id}`
- `POST /api/pedidos`
- `PUT /api/pedidos/{id}`
- `DELETE /api/pedidos/{id}`

## Ejemplo JSON para POST

```json
{
  "cliente": "Carlos Hernández",
  "fechaPedido": "2026-04-22",
  "total": 1899.90,
  "estado": "PENDIENTE"
}
```

## Ejecución

```bash
mvn spring-boot:run
```

## Requisitos

- Java 21
- Maven 3.9+
- MySQL
- Eureka Server ejecutándose en `http://localhost:8761`
