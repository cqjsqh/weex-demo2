<template>
  <div class="wrapper">
    <router-view/>
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
  .wrapper {
    justify-content: center;
    align-items: center;
  }
  .logo {
    width: 424px;
    height: 200px;
  }
  .greeting {
    text-align: center;
    margin-top: 70px;
    font-size: 50px;
    color: #41B883;
  }
  .message {
    margin: 30px;
    font-size: 32px;
    color: #727272;
  }
</style>
