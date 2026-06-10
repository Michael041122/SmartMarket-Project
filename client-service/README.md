# Client Service - Microservicio de Clientes

Microservicio REST para gestión de clientes. Puerto: **8085**

## Tecnologías
- Java 21 · Spring Boot 3.5.13 · Spring Cloud (Eureka) · MySQL · Lombok

## Base de datos
- Nombre: `client_service` (se crea automáticamente)

## Endpoints `/api/clientes`
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /api/clientes | Obtener todos |
| GET | /api/clientes/{id} | Obtener por ID |
| POST | /api/clientes | Crear cliente |
| PUT | /api/clientes/{id} | Actualizar cliente |
| DELETE | /api/clientes/{id} | Eliminar cliente |
| GET | /api/clientes/activos | Solo clientes activos |
| GET | /api/clientes/email/{email} | Buscar por email |

## Ejecutar
```bash
mvn spring-boot:run
```
