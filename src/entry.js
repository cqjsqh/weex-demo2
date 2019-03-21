/* global Vue */

/* weex initialized here, please do not move this line */
import router from './router';
import App from '@/App';
import mixins from '@/assets/mixins';

Vue.mixin(mixins);

/* eslint-disable no-new */
new Vue(Vue.util.extend({ el: '#root', router }, App));

router.push('/');
