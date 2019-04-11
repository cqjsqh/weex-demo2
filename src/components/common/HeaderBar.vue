<template>
    <div class="w">
        <div class="left">
            <slot name="left"></slot>
        </div>
        <text class="title">{{title}}</text>
        <div class="right">
            <slot name="right" class="right2">
                <icon-font src="&#xe602;"/>
            </slot>
        </div>
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
        z-index: 9;
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
import IconFont from '@/components/common/IconFont';

export default {
  props: {
    title: {
      type: String,
      default: '标题',
    },
  },
  components: {
    IconFont,
  },
  methods: {},
};
</script>
