<template>
    <div class="w" v-if="data">
        <div class="line"></div>
        <div class="left">
            <image class="icon" :src="data.avatar"></image>
            <text class="notice" v-if="data.notice">{{data.notice}}</text>
        </div>
        <div class="middle">
            <text class="name">{{data.author}}</text>
            <text class="desc">{{data.msg}}</text>
        </div>
        <text class="right">{{data.time}}</text>
    </div>
</template>
<style scoped>
    .w{
        width: 750px;
        padding: 15px;
        flex-direction: row;
        justify-content: space-between;
        background-color: #fff;
        position: relative;
    }
    .line{
        height: 1px;
        background-color: #b7b7b7;
        opacity: .5;
        position: absolute;
        left: 15px;
        right: 0;
        bottom: 0;
    }
    .left{
        position: relative;
    }
    .icon{
        width: 96px;
        height: 96px;
        border-radius: 10px;
    }
    .notice{
        min-width: 20px;
        height: 36px;
        line-height: 36px;
        padding: 0 8px;
        text-align: center;
        border-radius: 18px;
        background-color: #f43531;
        font-size: 24px;
        color: #fff;
        position: absolute;
        right: -18px;
        top: -10px;
    }
    .middle{
        flex: 1;
        justify-content: space-between;
        padding-left: 20px;
    }
    .name{
        font-size: 32px;
        color: #000;
        text-overflow: ellipsis;
    }
    .desc{
        font-size: 28px;
        color: #888;
        text-overflow: ellipsis;
    }
    .right{
        font-size: 26px;
        color: #b8b8b8;
    }
</style>
<script>
export default {
  props: {
    data: {
      type: Object,
      default: null,
    },
  },
  methods: {},
};
</script>
