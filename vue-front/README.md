# Vue Microservicios Front

Frontend Vue 3 conectado con los 5 microservicios Spring Boot.

## Requisitos
- Node.js 18+
- npm o yarn

## Instalación
```bash
npm install
```

## Ejecutar en desarrollo
```bash
npm run dev
```
Abre http://localhost:5173

## Configuración
El archivo `.env` ya contiene los puertos por defecto:
- product-service  → localhost:8082
- order-service    → localhost:8083
- sales-service    → localhost:8084
- client-service   → localhost:8085
- provider-service → localhost:8086

## Construir para producción
```bash
npm run build
```

## Estructura
```
src/
├── views/          # Una vista por módulo
├── services/       # Un servicio por microservicio
├── components/     # StatusCard reutilizable
├── router/         # Vue Router con 6 rutas
├── App.vue         # Layout con sidebar
└── styles.css      # Estilos globales
```
