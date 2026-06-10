import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '../views/DashboardView.vue'
import ProductosView from '../views/ProductosView.vue'
import PedidosView from '../views/PedidosView.vue'
import VentasView from '../views/VentasView.vue'
import ClientesView from '../views/ClientesView.vue'
import ProveedoresView from '../views/ProveedoresView.vue'

const routes = [
  { path: '/',            component: DashboardView,   name: 'dashboard'    },
  { path: '/productos',   component: ProductosView,   name: 'productos'    },
  { path: '/pedidos',     component: PedidosView,     name: 'pedidos'      },
  { path: '/ventas',      component: VentasView,      name: 'ventas'       },
  { path: '/clientes',    component: ClientesView,    name: 'clientes'     },
  { path: '/proveedores', component: ProveedoresView, name: 'proveedores'  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
