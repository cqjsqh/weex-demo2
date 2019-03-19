<template>
    <div class="w">
        <div class="item" v-for="(item, i) in config.tabTitles" :key="i" @click="tap(item)" >
            <icon-font class="icon"
                       :src="item.icon"
                       :class="[$route.path == item.path ? 'active' : '']"/>

            <text class="text"
                  :class="[$route.path == item.path ? 'active' : '']">{{item.title}}</text>
        </div>
    </div>
</template>
<style scoped>
    .w{
        height: 100px;
        flex-direction: row;
        flex-wrap: nowrap;
        justify-content: space-around;
        align-items: center;
        border-top-width: 1px;
        border-color: #b7b7b7;
        background-color: #f9f9f9;
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
    }
    .item{
        width: 100px;
        align-items: center;
        position: relative;
    }
    .icon{
        font-size: 50px;
        color: #797979;
    }
    .text{
        font-size: 20px;
        color: #797979;
        white-space: nowrap;
    }
    .active{
        color: #0bb908;
    }
</style>
<script>
import IconFont from '@/components/common/IconFont';

const config = {
  tabTitles: [
    {
      title: '微信',
      icon: '&#xe602;',
      path: '/',
    },
    {
      title: '通信录',
      icon: '&#xe69e;',
      path: '/contact',
    },
    {
      title: '发现',
      icon: '&#xe61c;',
      path: '/find',
    },
    {
      title: '我',
      icon: '&#xe609;',
      path: '/me',
    },
  ],
};

export default {
  components: {
    IconFont,
  },
  data() {
    return {
      config,
    };
  },
  methods: {
    tap({ path }) {
      this.$router.push(path);
    },
  },
};
</script>
