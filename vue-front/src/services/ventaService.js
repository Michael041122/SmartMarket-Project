import { ventaService as api } from './api.js'

export default {
  getAll:         ()          => api.get(''),
  getById:        (id)        => api.get(`${id}`),
  create:         (data)      => api.post('', data),
  update:         (id, data)  => api.put(`${id}`, data),
  delete:         (id)        => api.delete(`${id}`),
  getByCliente:   (cliente)   => api.get(`cliente/${cliente}`),
  getByProducto:  (pid)       => api.get(`producto/${pid}`),
}