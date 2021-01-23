import Vue from 'vue';
import VueRouter, { RouteConfig } from 'vue-router';

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
    // Loading different pages
    {
        path: '/',
        alias: '/welcome',
        name: 'welcome',
        component: () => import('../pages/IndexPage.vue')
    },
    {
        path: '/user',
        name: 'user',
        component: () => import('../pages/UserPage.vue')
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('../pages/SignUpPage.vue')
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router;