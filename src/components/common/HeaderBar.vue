<template>
    <div class="w">
        <text class="title">{{title}}</text>
    </div>
</template>
<style scoped>
    .w{
        height: 90px;
        flex-direction: row;
        align-items: center;
        background-color: #1b1b1b;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
    }
    .title{
        flex: 1;
        font-size: 34px;
        text-align: center;
        color: #fff;
        text-overflow: ellipsis;
    }
</style>
<script>
export default {
  props: {
    title: {
      type: String,
      default: '标题',
    },
  },
  methods: {},
};
</script>
