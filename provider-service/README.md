# Provider Service - Microservicio de Proveedores

Microservicio REST para gestión de proveedores. Puerto: **8086**

## Tecnologías
- Java 21 · Spring Boot 3.5.13 · Spring Cloud (Eureka) · MySQL · Lombok

## Base de datos
- Nombre: `provider_service` (se crea automáticamente)

## Endpoints `/api/proveedores`
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /api/proveedores | Obtener todos |
| GET | /api/proveedores/{id} | Obtener por ID |
| POST | /api/proveedores | Crear proveedor |
| PUT | /api/proveedores/{id} | Actualizar proveedor |
| DELETE | /api/proveedores/{id} | Eliminar proveedor |
| GET | /api/proveedores/activos | Solo activos |
| GET | /api/proveedores/email/{email} | Buscar por email |
| GET | /api/proveedores/empresa/{empresa} | Buscar por empresa |

## Ejecutar
```bash
mvn spring-boot:run
```
