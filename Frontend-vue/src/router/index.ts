import { createWebHistory, createRouter, RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'info',
    component: () => import('../components/Top.vue'),
  },
  {
    path: '/customers',
    name: 'customers',
    component: () => import('../components/CustomerList.vue'),
  },
  {
    path: '/tags',
    name: 'tags',
    component: () => import('../components/TagList.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
