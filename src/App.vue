<template>
    <div>
        <router-view class="app-wrapper"/>
    </div>
</template>

<script>
export default {
  name: 'App',
  components: {},
  data() {
    return {
    };
  },
  beforeCreate() {
    weex.requireModule('dom').addRule('fontFace', {
      fontFamily: 'iconfont',
      src: "url('//at.alicdn.com/t/font_1092965_n6kcyeci4a.ttf')",
    });
  },
};
</script>

<style scoped>
    .app-wrapper {
        position: absolute;
        top:0;
        left: 0;
        right: 0;
        bottom: 0;
        z-index: 0;
    }
</style>
