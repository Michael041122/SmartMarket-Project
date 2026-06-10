# Sales Service - Microservicio de Ventas

Microservicio REST para gestión de ventas. Puerto: **8084**

## Tecnologías
- Java 21 · Spring Boot 3.5.13 · Spring Cloud (Eureka) · MySQL · Lombok

## Base de datos
- Nombre: `sales_service` (se crea automáticamente)

## Endpoints `/api/ventas`
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /api/ventas | Obtener todas |
| GET | /api/ventas/{id} | Obtener por ID |
| POST | /api/ventas | Crear venta |
| PUT | /api/ventas/{id} | Actualizar venta |
| DELETE | /api/ventas/{id} | Eliminar venta |
| GET | /api/ventas/cliente/{cliente} | Filtrar por cliente |
| GET | /api/ventas/producto/{productoId} | Filtrar por producto |

## Ejecutar
```bash
mvn spring-boot:run
```

## Configurar en application.properties
```properties
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```
