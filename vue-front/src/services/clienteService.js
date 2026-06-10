import { clienteService as api } from './api.js'

export default {
  getAll:         ()          => api.get(''),               // Quitamos la "/"
  getById:        (id)        => api.get(`${id}`),          // Quitamos la "/" inicial
  create:         (data)      => api.post('', data),        // Quitamos la "/"
  update:         (id, data)  => api.put(`${id}`, data),    // Quitamos la "/" inicial
  delete:         (id)        => api.delete(`${id}`),       // Quitamos la "/" inicial
  getActivos:     ()          => api.get('activos'),        // Quitamos la "/" inicial
  getByEmail:     (email)     => api.get(`email/${email}`), // Quitamos la "/" inicial
}