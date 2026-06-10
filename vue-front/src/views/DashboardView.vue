<template>
  <div>
    <div class="page-header">
      <h2>Dashboard</h2>
      <p>Resumen general del sistema de microservicios</p>
    </div>

    <div class="stats-grid">
      <StatusCard label="Productos"   :value="counts.productos"   sub="registrados"  color="#6c8aff" />
      <StatusCard label="Pedidos"     :value="counts.pedidos"     sub="en sistema"   color="#a78bfa" />
      <StatusCard label="Ventas"      :value="counts.ventas"      sub="realizadas"   color="#34d399" />
      <StatusCard label="Clientes"    :value="counts.clientes"    sub="registrados"  color="#fbbf24" />
      <StatusCard label="Proveedores" :value="counts.proveedores" sub="activos"      color="#f87171" />
    </div>

    <div class="stats-grid" style="margin-bottom: 0">
      <div class="card" v-for="svc in services" :key="svc.name" style="padding:18px 20px">
        <div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:8px">
          <span style="font-size:13px;font-weight:600">{{ svc.name }}</span>
          <span class="badge" :class="svc.ok ? 'badge-green' : 'badge-red'">
            {{ svc.ok ? '● online' : '● offline' }}
          </span>
        </div>
        <div style="font-size:11px;color:var(--text2);font-family:var(--mono)">localhost:{{ svc.port }}</div>
        <div style="font-size:11px;color:var(--text2);margin-top:4px">{{ svc.endpoint }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import StatusCard from '../components/StatusCard.vue'
import productoSvc  from '../services/productoService.js'
import pedidoSvc    from '../services/pedidoService.js'
import ventaSvc     from '../services/ventaService.js'
import clienteSvc   from '../services/clienteService.js'
import proveedorSvc from '../services/proveedorService.js'

const counts = ref({ productos: '–', pedidos: '–', ventas: '–', clientes: '–', proveedores: '–' })

const services = ref([
  { name: 'product-service',  port: 8082, endpoint: '/api/productos',   ok: false },
  { name: 'order-service',    port: 8083, endpoint: '/api/pedidos',     ok: false },
  { name: 'sales-service',    port: 8084, endpoint: '/api/ventas',      ok: false },
  { name: 'client-service',   port: 8085, endpoint: '/api/clientes',    ok: false },
  { name: 'provider-service', port: 8086, endpoint: '/api/proveedores', ok: false },
])

onMounted(async () => {
  const load = async (svc, key, idx) => {
    try {
      const r = await svc.getAll()
      counts.value[key] = r.data.length
      services.value[idx].ok = true
    } catch { counts.value[key] = 'err' }
  }
  load(productoSvc,  'productos',   0)
  load(pedidoSvc,    'pedidos',     1)
  load(ventaSvc,     'ventas',      2)
  load(clienteSvc,   'clientes',    3)
  load(proveedorSvc, 'proveedores', 4)
})
</script>
