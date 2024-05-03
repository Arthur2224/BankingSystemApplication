import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('../components/Login.vue')
    },
    {
      path: '/signUp',
      name: 'registration',
      component: () => import('../components/RegistrationForm.vue')
    },
    {
      path: '/mainMenu/:id',
      name: 'mainMenu',
      component: () => import('../components/AccountCabinet.vue')
    }
  ]
})
export default router
