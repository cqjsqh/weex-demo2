<template>
    <div class="w">
        <image class="icon" :src="data.icon" v-if="data.icon"></image>

        <text class="text">{{data.text}}</text>
        <slot></slot>
        <div class="line"></div>
    </div>
</template>
<style scoped>
    .w{
        padding: 20px 30px;
        flex-direction: row;
        align-items: center;
    }
    .line{
        height: 1px;
        background-color: rgba(182, 182, 182, .5);
        position: absolute;
        left: 30px;
        right: 0;
        bottom: 0;
    }
    .icon{
        width: 64px;
        height: 64px;
        font-size: 64px;
        border-radius: 4px;
        margin-right: 20px;
    }
    .text{
        flex: 1;
        font-size: 30px;
        color: #000;
    }
</style>
<script>
export default {
  props: {
    data: {
      type: Object,
      default() {
        return {
          icon: 'https://sinacloud.net/vue-wechat/images/headers/header01.png',
          text: '文本',
        };
      },
    },
  },
  methods: {},
};
</script>
