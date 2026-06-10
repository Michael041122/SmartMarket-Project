# Order Service - Microservicio de Pedidos

Microservicio REST para gestión de pedidos. Puerto: **8083**

## Tecnologías
- Java 21 · Spring Boot 3.5.13 · Spring Cloud (Eureka) · MySQL · Lombok

## Base de datos
- Nombre: `order_service` (se crea automáticamente)

## Endpoints `/api/pedidos`
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /api/pedidos | Obtener todos |
| GET | /api/pedidos/{id} | Obtener por ID |
| POST | /api/pedidos | Crear pedido |
| PUT | /api/pedidos/{id} | Actualizar pedido |
| DELETE | /api/pedidos/{id} | Eliminar pedido |
| GET | /api/pedidos/cliente/{cliente} | Filtrar por cliente |

## Ejecutar
```bash
mvn spring-boot:run
```

## Configurar en application.properties
```properties
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```
