import { productoService as api } from './api.js'

export default {
  getAll:           ()          => api.get(''),                // Sin la barra "/"
  getById:          (id)        => api.get(`${id}`),           // Quitamos la "/" antes de la id
  create:           (data)      => api.post('', data),         // Sin la barra "/"
  update:           (id, data)  => api.put(`${id}`, data),     // Quitamos la "/"
  delete:           (id)        => api.delete(`${id}`),        // Quitamos la "/"
  getDisponibilidad:(id)        => api.get(`${id}/disponibilidad`), // Quitamos la "/" inicial
}