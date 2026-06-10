import axios from 'axios'
const BASE = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'
export const productoService = axios.create({ baseURL: `${BASE}/api/productos` })
export const pedidoService   = axios.create({ baseURL: `${BASE}/api/pedidos`   })
export const ventaService    = axios.create({ baseURL: `${BASE}/api/ventas`    })
export const clienteService  = axios.create({ baseURL: `${BASE}/api/clientes`  })
export const proveedorService= axios.create({ baseURL: `${BASE}/api/proveedores`})
