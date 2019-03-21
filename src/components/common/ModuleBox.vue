<template>
    <div class="w">
        <div class="line top"></div>
        <slot></slot>
        <div class="line bottom"></div>
    </div>
</template>
<style scoped>
    .w{
        background-color: #fff;
        position: relative;
    }
    .line{
        height: 1px;
        background-color: rgba(182, 182, 182, .5);
        position: absolute;
        left: 0;
        right: 0;
        z-index: 1;
    }
    .top{
        top: 0;
    }
    .bottom{
        bottom: 0;
    }
</style>
<script>
export default {
  methods: {},
};
</script>
