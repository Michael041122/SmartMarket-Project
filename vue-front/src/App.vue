<template>
  <div class="layout">
    <aside class="sidebar">
      <div class="sidebar-brand">
        <h1>⚡ MicroSys</h1>
        <p>Panel de Administración</p>
      </div>
      <nav class="sidebar-nav">
        <router-link v-for="item in navItems" :key="item.to"
          :to="item.to" class="nav-item"
          :class="{ active: $route.path === item.to }">
          <span class="icon">{{ item.icon }}</span>
          {{ item.label }}
        </router-link>
      </nav>
      <div class="sidebar-footer">
        5 microservicios conectados
      </div>
    </aside>
    <main class="main">
      <router-view />
    </main>
  </div>
</template>

<script setup>
const navItems = [
  { to: '/',            icon: '🏠', label: 'Dashboard'   },
  { to: '/productos',   icon: '📦', label: 'Productos'   },
  { to: '/pedidos',     icon: '🛒', label: 'Pedidos'     },
  { to: '/ventas',      icon: '💰', label: 'Ventas'      },
  { to: '/clientes',    icon: '👥', label: 'Clientes'    },
  { to: '/proveedores', icon: '🏭', label: 'Proveedores' },
]
</script>
