<template>
  <div>
    <div class="page-header">
      <h2>💰 Ventas</h2>
      <p>Gestión de ventas — port 8084</p>
    </div>
    <div class="alert alert-error"   v-if="error">{{ error }}</div>
    <div class="alert alert-success" v-if="success">{{ success }}</div>

    <div class="card">
      <div class="card-header">
        <h3>Registro de Ventas ({{ items.length }})</h3>
        <div class="card-actions">
          <input v-model="search" class="input" placeholder="Buscar cliente…" style="width:180px" />
          <button class="btn btn-success" @click="openModal()">+ Nueva Venta</button>
          <button class="btn btn-ghost" @click="load()">↻ Actualizar</button>
        </div>
      </div>
      <div class="loading" v-if="loading"><span class="spinner"></span>Cargando…</div>
      <div class="table-wrap" v-else>
        <div class="empty" v-if="!filtered.length"><div class="icon">💰</div><p>Sin ventas registradas</p></div>
        <table v-else>
          <thead><tr><th>#ID</th><th>Cliente</th><th>Producto ID</th><th>Cantidad</th><th>Total</th><th>Fecha</th><th>Acciones</th></tr></thead>
          <tbody>
            <tr v-for="v in filtered" :key="v.id">
              <td class="mono">#{{ v.id }}</td>
              <td>{{ v.cliente }}</td>
              <td class="mono">{{ v.productoId }}</td>
              <td class="mono">{{ v.cantidad }}</td>
              <td class="mono" style="color:var(--green)">${{ v.total?.toLocaleString('es-MX') }}</td>
              <td style="color:var(--text2)">{{ v.fecha }}</td>
              <td>
                <button class="btn btn-ghost btn-sm" @click="openModal(v)">Editar</button>
                <button class="btn btn-danger btn-sm" style="margin-left:6px" @click="remove(v.id)">Eliminar</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="modal-overlay" v-if="showModal" @click.self="showModal=false">
      <div class="modal">
        <h3>{{ form.id ? 'Editar Venta' : 'Nueva Venta' }}</h3>
        <div class="form-group"><label>Cliente</label><input v-model="form.cliente" class="input" placeholder="Nombre del cliente" /></div>
        <div class="form-group"><label>Producto ID</label><input v-model.number="form.productoId" type="number" class="input" /></div>
        <div class="form-group"><label>Cantidad</label><input v-model.number="form.cantidad" type="number" class="input" /></div>
        <div class="form-group"><label>Total ($)</label><input v-model.number="form.total" type="number" class="input" /></div>
        <div class="form-group"><label>Fecha</label><input v-model="form.fecha" class="input" placeholder="2024-01-15" /></div>
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
import svc from '../services/ventaService.js'

const items = ref([]), loading = ref(false), error = ref(''), success = ref('')
const search = ref(''), showModal = ref(false), saving = ref(false)
const form = ref({ id: null, cliente: '', productoId: null, cantidad: 1, total: 0, fecha: '' })

const filtered = computed(() =>
  items.value.filter(v => v.cliente?.toLowerCase().includes(search.value.toLowerCase())))

async function load() {
  loading.value = true; error.value = ''
  try { items.value = (await svc.getAll()).data }
  catch { error.value = 'No se pudo conectar con sales-service (puerto 8084)' }
  finally { loading.value = false }
}
function openModal(v = null) {
  form.value = v ? { ...v } : { id: null, cliente: '', productoId: null, cantidad: 1, total: 0, fecha: '' }
  showModal.value = true
}
async function save() {
  saving.value = true; error.value = ''; success.value = ''
  try {
    if (form.value.id) await svc.update(form.value.id, form.value)
    else await svc.create(form.value)
    success.value = 'Venta guardada'; showModal.value = false; load()
  } catch { error.value = 'Error al guardar la venta' }
  finally { saving.value = false }
}
async function remove(id) {
  if (!confirm('¿Eliminar esta venta?')) return
  try { await svc.delete(id); success.value = 'Venta eliminada'; load() }
  catch { error.value = 'Error al eliminar' }
}
onMounted(load)
</script>
