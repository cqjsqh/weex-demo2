<template>
    <div>
        <text class="message">Now, let's use Vue.js to build your Weex app.</text>
        <text>版本1</text>
        <text @click="tel">拨打电话</text>
        <webView ref="myWeb" @finish="finish" src="https://www.taobao.com/" style="width: 720px; height:600px;"></webView>
    </div>
</template>

<script>
const modal = weex.requireModule('modal');

export default {
  methods: {
    tel() {
      try {
        weex.requireModule('tel').call('14455554444');
      } catch ({ message }) {
        modal.alert({ message });
      }
    },
    finish() {
      this.$refs.myWeb.evaluateJavascript('console.log("console打印")');
    },
  },
};
</script>
