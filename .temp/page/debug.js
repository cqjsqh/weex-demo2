import Vue from 'vue'
import weex from 'weex-vue-render'
weex.init(Vue)
/* weex initialized here, please do not move this line */

import App from '..\\..\\src\\pages\\Debug.vue'
import mixins from '@/assets/mixins'

Vue.mixin(mixins);
new Vue(Vue.util.extend({ el: '#root' }, App))