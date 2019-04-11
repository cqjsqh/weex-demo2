import Vue from 'vue'
import weex from 'weex-vue-render'
/* global Vue */

weex.init(Vue)
/* weex initialized here, please do not move this line */
import router from './router';
import App from '@/App';
import wx from '@/assets/wx';
import mixins from '@/assets/mixins';

// global.wx = wx;
Vue.prototype.wx = wx;
Vue.mixin(mixins);

/* eslint-disable no-new */
new Vue(Vue.util.extend({ el: '#root', router }, App));

router.push('/');

// modifications need to be start
