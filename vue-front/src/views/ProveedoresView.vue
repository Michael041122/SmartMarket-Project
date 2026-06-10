<template>
  <div>
    <div class="page-header">
      <h2>🏭 Proveedores</h2>
      <p>Gestión de proveedores — port 8086</p>
    </div>
    <div class="alert alert-error"   v-if="error">{{ error }}</div>
    <div class="alert alert-success" v-if="success">{{ success }}</div>

    <div class="card">
      <div class="card-header">
        <h3>Lista de Proveedores ({{ items.length }})</h3>
        <div class="card-actions">
          <input v-model="search" class="input" placeholder="Buscar empresa…" style="width:180px" />
          <button class="btn btn-primary" @click="openModal()">+ Nuevo</button>
          <button class="btn btn-ghost" @click="load()">↻ Actualizar</button>
        </div>
      </div>
      <div class="loading" v-if="loading"><span class="spinner"></span>Cargando…</div>
      <div class="table-wrap" v-else>
        <div class="empty" v-if="!filtered.length"><div class="icon">🏭</div><p>Sin proveedores registrados</p></div>
        <table v-else>
          <thead><tr><th>#ID</th><th>Nombre</th><th>Empresa</th><th>Email</th><th>Teléfono</th><th>Estado</th><th>Acciones</th></tr></thead>
          <tbody>
            <tr v-for="p in filtered" :key="p.id">
              <td class="mono">#{{ p.id }}</td>
              <td>{{ p.nombre }}</td>
              <td><strong>{{ p.empresa }}</strong></td>
              <td style="color:var(--accent)">{{ p.email }}</td>
              <td class="mono">{{ p.telefono }}</td>
              <td><span class="badge" :class="p.activo ? 'badge-green' : 'badge-red'">{{ p.activo ? 'Activo' : 'Inactivo' }}</span></td>
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
        <h3>{{ form.id ? 'Editar Proveedor' : 'Nuevo Proveedor' }}</h3>
        <div class="form-group"><label>Nombre</label><input v-model="form.nombre" class="input" /></div>
        <div class="form-group"><label>Empresa</label><input v-model="form.empresa" class="input" /></div>
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
import svc from '../services/proveedorService.js'

const items = ref([]), loading = ref(false), error = ref(''), success = ref('')
const search = ref(''), showModal = ref(false), saving = ref(false)
const form = ref({ id: null, nombre: '', empresa: '', email: '', telefono: '', direccion: '', activo: true })

const filtered = computed(() =>
  items.value.filter(p =>
    `${p.nombre} ${p.empresa}`.toLowerCase().includes(search.value.toLowerCase())))

async function load() {
  loading.value = true; error.value = ''
  try { items.value = (await svc.getAll()).data }
  catch { error.value = 'No se pudo conectar con provider-service (puerto 8086)' }
  finally { loading.value = false }
}
function openModal(p = null) {
  form.value = p ? { ...p } : { id: null, nombre: '', empresa: '', email: '', telefono: '', direccion: '', activo: true }
  showModal.value = true
}
async function save() {
  saving.value = true; error.value = ''; success.value = ''
  try {
    if (form.value.id) await svc.update(form.value.id, form.value)
    else await svc.create(form.value)
    success.value = 'Proveedor guardado'; showModal.value = false; load()
  } catch { error.value = 'Error al guardar' }
  finally { saving.value = false }
}
async function remove(id) {
  if (!confirm('¿Eliminar este proveedor?')) return
  try { await svc.delete(id); success.value = 'Proveedor eliminado'; load() }
  catch { error.value = 'Error al eliminar' }
}
onMounted(load)
</script>
