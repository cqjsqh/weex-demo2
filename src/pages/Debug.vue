<template>
    <div>
        <text @click="tel">拨打电话</text>
        <text @click="navigator">Navigator</text>
        <webView ref="myWeb" @finish="finish" src="https://www.baidu.com/" style="width: 720px; height:200px;"></webView>
    </div>
</template>

<script>
const modal = weex.requireModule('modal');

function M(moduleName) {
  const module = weex.requireModule(moduleName);
  if (module === undefined) {
    console.warn(`模块【${moduleName}】未注册`);
  } else if (module && Object.keys(module).length === 0){
    console.warn(`模块${moduleName}已注册,但环境不支持`);
  }
  return module;
}

export default {
  created() {},
  methods: {
    tel() {
      try {
        M('tel').call('14455554444');
      } catch ({ message }) {
        modal.alert({ message });
      }
    },
    finish() {
      // this.$refs.myWeb.evaluateJavascript('console.log("console打印")');
    },
    navigator() {
      M(navigator).push({
        url: '',
        animated: 'true',
      });
    },
  },
};
</script>
