<template>
  <div>
    <div class="page-header">
      <h2>📦 Productos</h2>
      <p>Gestión del catálogo de productos — port 8082</p>
    </div>

    <div class="alert alert-error" v-if="error">{{ error }}</div>
    <div class="alert alert-success" v-if="success">{{ success }}</div>

    <div class="card">
      <div class="card-header">
        <h3>Lista de Productos ({{ items.length }})</h3>
        <div class="card-actions">
          <input v-model="search" class="input" placeholder="Buscar…" style="width:180px" />
          <button class="btn btn-primary" @click="openModal()">+ Nuevo</button>
          <button class="btn btn-ghost" @click="load()">↻ Actualizar</button>
        </div>
      </div>
      <div class="loading" v-if="loading"><span class="spinner"></span>Cargando…</div>
      <div class="table-wrap" v-else>
        <div class="empty" v-if="!filtered.length">
          <div class="icon">📦</div>
          <p>No hay productos registrados</p>
        </div>
        <table v-else>
          <thead>
            <tr>
              <th>#ID</th>
              <th>Nombre</th>
              <th>Precio</th>
              <th>Disponible</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="p in filtered" :key="p.id">
              <td class="mono">#{{ p.id }}</td>
              <td>{{ p.nombre }}</td>
              <td class="mono">${{ p.precio?.toLocaleString('es-MX') }}</td>
              <td>
                <span class="badge" :class="p.disponible ? 'badge-green' : 'badge-red'">
                  {{ p.disponible ? 'Disponible' : 'No disponible' }}
                </span>
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

    <!-- Modal -->
    <div class="modal-overlay" v-if="showModal" @click.self="showModal=false">
      <div class="modal">
        <h3>{{ form.id ? 'Editar Producto' : 'Nuevo Producto' }}</h3>
        <div class="form-group">
          <label>Nombre</label>
          <input v-model="form.nombre" class="input" placeholder="Ej. Laptop" />
        </div>
        <div class="form-group">
          <label>Precio</label>
          <input v-model.number="form.precio" type="number" class="input" placeholder="0.00" />
        </div>
        <div class="form-group">
          <label>Disponible</label>
          <select v-model="form.disponible" class="select">
            <option :value="true">Sí</option>
            <option :value="false">No</option>
          </select>
        </div>
        <div class="form-actions">
          <button class="btn btn-ghost" @click="showModal=false">Cancelar</button>
          <button class="btn btn-primary" @click="save()" :disabled="saving">
            {{ saving ? 'Guardando…' : 'Guardar' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import svc from '../services/productoService.js'

const items = ref([]), loading = ref(false), error = ref(''), success = ref('')
const search = ref(''), showModal = ref(false), saving = ref(false)
const form = ref({ id: null, nombre: '', precio: 0, disponible: true })

const filtered = computed(() =>
  items.value.filter(p => p.nombre?.toLowerCase().includes(search.value.toLowerCase())))

async function load() {
  loading.value = true; error.value = ''
  try { items.value = (await svc.getAll()).data }
  catch (e) { error.value = 'No se pudo conectar con product-service (puerto 8082)' }
  finally { loading.value = false }
}

function openModal(p = null) {
  form.value = p ? { ...p } : { id: null, nombre: '', precio: 0, disponible: true }
  showModal.value = true
}

async function save() {
  saving.value = true; error.value = ''; success.value = ''
  try {
    if (form.value.id) await svc.update(form.value.id, form.value)
    else await svc.create(form.value)
    success.value = form.value.id ? 'Producto actualizado' : 'Producto creado'
    showModal.value = false; load()
  } catch { error.value = 'Error al guardar el producto' }
  finally { saving.value = false }
}

async function remove(id) {
  if (!confirm('¿Eliminar este producto?')) return
  try { await svc.delete(id); success.value = 'Producto eliminado'; load() }
  catch { error.value = 'Error al eliminar' }
}

onMounted(load)
</script>
