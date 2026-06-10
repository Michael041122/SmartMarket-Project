<template>
  <div>
    <div class="page-header">
      <h2>👥 Clientes</h2>
      <p>Gestión de clientes — port 8085</p>
    </div>
    <div class="alert alert-error"   v-if="error">{{ error }}</div>
    <div class="alert alert-success" v-if="success">{{ success }}</div>

    <div class="card">
      <div class="card-header">
        <h3>Lista de Clientes ({{ items.length }})</h3>
        <div class="card-actions">
          <input v-model="search" class="input" placeholder="Buscar…" style="width:180px" />
          <button class="btn btn-primary" @click="openModal()">+ Nuevo</button>
          <button class="btn btn-ghost" @click="load()">↻ Actualizar</button>
        </div>
      </div>
      <div class="loading" v-if="loading"><span class="spinner"></span>Cargando…</div>
      <div class="table-wrap" v-else>
        <div class="empty" v-if="!filtered.length"><div class="icon">👥</div><p>Sin clientes registrados</p></div>
        <table v-else>
          <thead><tr><th>#ID</th><th>Nombre</th><th>Email</th><th>Teléfono</th><th>Dirección</th><th>Estado</th><th>Acciones</th></tr></thead>
          <tbody>
            <tr v-for="c in filtered" :key="c.id">
              <td class="mono">#{{ c.id }}</td>
              <td><strong>{{ c.nombre }} {{ c.apellido }}</strong></td>
              <td style="color:var(--accent)">{{ c.email }}</td>
              <td class="mono">{{ c.telefono }}</td>
              <td style="color:var(--text2);font-size:12px">{{ c.direccion }}</td>
              <td><span class="badge" :class="c.activo ? 'badge-green' : 'badge-red'">{{ c.activo ? 'Activo' : 'Inactivo' }}</span></td>
              <td>
                <button class="btn btn-ghost btn-sm" @click="openModal(c)">Editar</button>
                <button class="btn btn-danger btn-sm" style="margin-left:6px" @click="remove(c.id)">Eliminar</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="modal-overlay" v-if="showModal" @click.self="showModal=false">
      <div class="modal">
        <h3>{{ form.id ? 'Editar Cliente' : 'Nuevo Cliente' }}</h3>
        <div class="form-group"><label>Nombre</label><input v-model="form.nombre" class="input" /></div>
        <div class="form-group"><label>Apellido</label><input v-model="form.apellido" class="input" /></div>
        <div class="form-group"><label>Email</label><input v-model="form.email" class="input" type="email" /></div>
        <div class="form-group"><label>Teléfono</label><input v-model="form.telefono" class="input" /></div>
        <div class="form-group"><label>Dirección</label><input v-model="form.direccion" class="input" /></div>
        <div class="form-group">
          <label>Estado</label>
          <select v-model="form.activo" class="select">
            <option :value="true">Activo</option>
            <option :value="false">Inactivo</option>
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
import svc from '../services/clienteService.js'

const items = ref([]), loading = ref(false), error = ref(''), success = ref('')
const search = ref(''), showModal = ref(false), saving = ref(false)
const form = ref({ id: null, nombre: '', apellido: '', email: '', telefono: '', direccion: '', activo: true })

const filtered = computed(() =>
  items.value.filter(c =>
    `${c.nombre} ${c.apellido} ${c.email}`.toLowerCase().includes(search.value.toLowerCase())))

async function load() {
  loading.value = true; error.value = ''
  try { items.value = (await svc.getAll()).data }
  catch { error.value = 'No se pudo conectar con client-service (puerto 8085)' }
  finally { loading.value = false }
}
function openModal(c = null) {
  form.value = c ? { ...c } : { id: null, nombre: '', apellido: '', email: '', telefono: '', direccion: '', activo: true }
  showModal.value = true
}
async function save() {
  saving.value = true; error.value = ''; success.value = ''
  try {
    if (form.value.id) await svc.update(form.value.id, form.value)
    else await svc.create(form.value)
    success.value = 'Cliente guardado'; showModal.value = false; load()
  } catch { error.value = 'Error al guardar' }
  finally { saving.value = false }
}
async function remove(id) {
  if (!confirm('¿Eliminar este cliente?')) return
  try { await svc.delete(id); success.value = 'Cliente eliminado'; load() }
  catch { error.value = 'Error al eliminar' }
}
onMounted(load)
</script>
