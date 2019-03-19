<template>
    <text class="iconfont">{{src | heDecode}}</text>
</template>
<style scoped>
    .iconfont {
        font-family: iconfont;
    }
</style>
<script>
export default {
  props: {
    src: {
      type: String,
      default: '&#xe609;',
    },
  },
  filters: {
    heDecode(val) {
      return unescape(val.replace(/^&#x(.*);$/, '%u$1'));
    },
  },
};
</script>
