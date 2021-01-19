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
        path: '/sprint1',
        name: 'sprint-1',
        component: () => import('../pages/Sprint1Page.vue')
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router;