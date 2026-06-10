<template>
  <div>
    <div class="page-header">
      <h2>🛒 Pedidos</h2>
      <p>Gestión de pedidos — port 8083</p>
    </div>

    <div class="alert alert-error"   v-if="error">{{ error }}</div>
    <div class="alert alert-success" v-if="success">{{ success }}</div>

    <div class="card">
      <div class="card-header">
        <h3>Lista de Pedidos ({{ items.length }})</h3>
        <div class="card-actions">
          <input v-model="search" class="input" placeholder="Buscar cliente…" style="width:180px" />
          <button class="btn btn-primary" @click="openModal()">+ Nuevo</button>
          <button class="btn btn-ghost" @click="load()">↻ Actualizar</button>
        </div>
      </div>
      <div class="loading" v-if="loading"><span class="spinner"></span>Cargando…</div>
      <div class="table-wrap" v-else>
        <div class="empty" v-if="!filtered.length"><div class="icon">🛒</div><p>Sin pedidos registrados</p></div>
        <table v-else>
          <thead><tr><th>#ID</th><th>Cliente</th><th>Producto ID</th><th>Cantidad</th><th>Estado</th><th>Acciones</th></tr></thead>
          <tbody>
            <tr v-for="p in filtered" :key="p.id">
              <td class="mono">#{{ p.id }}</td>
              <td>{{ p.cliente }}</td>
              <td class="mono">{{ p.productoId }}</td>
              <td class="mono">{{ p.cantidad }}</td>
              <td>
                <span class="badge" :class="estadoBadge(p.estado)">{{ p.estado }}</span>
              </td>
              <td>
                <button class="btn btn-ghost btn-sm" @click="openModal(p)">Editar</button>
                <button class="btn btn-danger btn-sm" style="margin-left:6px" @click="remove(p.id)">Eliminar</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="modal-overlay" v-if="showModal" @click.self="showModal=false">
      <div class="modal">
        <h3>{{ form.id ? 'Editar Pedido' : 'Nuevo Pedido' }}</h3>
        <div class="form-group"><label>Cliente</label><input v-model="form.cliente" class="input" placeholder="Nombre del cliente" /></div>
        <div class="form-group"><label>Producto ID</label><input v-model.number="form.productoId" type="number" class="input" placeholder="ID del producto" /></div>
        <div class="form-group"><label>Cantidad</label><input v-model.number="form.cantidad" type="number" class="input" placeholder="1" /></div>
        <div class="form-group">
          <label>Estado</label>
          <select v-model="form.estado" class="select">
            <option>PENDIENTE</option><option>ENTREGADO</option><option>CANCELADO</option>
          </select>
        </div>
        <div class="form-actions">
          <button class="btn btn-ghost" @click="showModal=false">Cancelar</button>
          <button class="btn btn-primary" @click="save()" :disabled="saving">{{ saving ? 'Guardando…' : 'Guardar' }}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import svc from '../services/pedidoService.js'

const items = ref([]), loading = ref(false), error = ref(''), success = ref('')
const search = ref(''), showModal = ref(false), saving = ref(false)
const form = ref({ id: null, cliente: '', productoId: null, cantidad: 1, estado: 'PENDIENTE' })

const filtered = computed(() =>
  items.value.filter(p => p.cliente?.toLowerCase().includes(search.value.toLowerCase())))

const estadoBadge = (e) => ({ PENDIENTE: 'badge-yellow', ENTREGADO: 'badge-green', CANCELADO: 'badge-red' }[e] || 'badge-blue')

async function load() {
  loading.value = true; error.value = ''
  try { items.value = (await svc.getAll()).data }
  catch { error.value = 'No se pudo conectar con order-service (puerto 8083)' }
  finally { loading.value = false }
}
function openModal(p = null) {
  form.value = p ? { ...p } : { id: null, cliente: '', productoId: null, cantidad: 1, estado: 'PENDIENTE' }
  showModal.value = true
}
async function save() {
  saving.value = true; error.value = ''; success.value = ''
  try {
    if (form.value.id) await svc.update(form.value.id, form.value)
    else await svc.create(form.value)
    success.value = 'Pedido guardado'; showModal.value = false; load()
  } catch { error.value = 'Error al guardar el pedido' }
  finally { saving.value = false }
}
async function remove(id) {
  if (!confirm('¿Eliminar este pedido?')) return
  try { await svc.delete(id); success.value = 'Pedido eliminado'; load() }
  catch { error.value = 'Error al eliminar' }
}
onMounted(load)
</script>
