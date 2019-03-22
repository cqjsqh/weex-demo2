<template>
    <div>
        <text @click="tel">拨打电话</text>
        <text @click="navigator">Navigator</text>
        <webView ref="myWeb" @finish="finish" src="https://www.baidu.com/" style="width: 720px; height:200px;"></webView>
    </div>
</template>

<script>
export default {
  created() {},
  methods: {
    tel() {
      try {
        this.M('tel').call('14455554444');
      } catch ({ message }) {
        this.M('modal').alert({ message });
      }
    },
    finish() {
      // this.$refs.myWeb.evaluateJavascript('console.log("console打印")');
    },
    navigator() {
      this.M('navigator').push({
        url: '',
        animated: 'true',
      });
    },
  },
};
</script>
