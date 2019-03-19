/* global Vue */
import Router from 'vue-router';

import Home from '@/components/Home';
import Contact from '@/components/Contact';
import Find from '@/components/Find';
import Me from '@/components/Me';

Vue.use(Router);

const router = new Router({
  routes: [
    { path: '/', component: Home },
    { path: '/contact', component: Contact },
    { path: '/find', component: Find },
    { path: '/me', component: Me },
  ],
});

export default router;
