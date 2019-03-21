<template>
    <div class="w" v-if="data">
        <div class="line"></div>
        <div class="left">
            <image class="icon" :src="data.avatar"></image>
            <div class="notice" v-if="data.notice"><text class="notice-text">{{data.notice}}</text></div>
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
        padding: 0 15px;
        flex-direction: row;
        background-color: #fff;
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
        padding: 15px 20px 15px 0;
    }
    .icon{
        width: 96px;
        height: 96px;
        border-radius: 10px;
    }
    .notice{
        align-items: center;
        justify-content: center;
        min-width: 36px;
        height: 36px;
        padding: 0 8px;
        border-radius: 18px;
        background-color: #f43531;
        position: absolute;
        right: 3px;
        top: 5px;
        z-index: 2;
    }
    .notice-text{
        font-size: 24px;
        color: #fff;
    }
    .middle{
        padding: 15px 0;
        flex: 1;
        justify-content: space-between;
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
        padding: 15px 0;
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
