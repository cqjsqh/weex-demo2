<template>
    <scroller class="page">
        <header-bar title="微信"/>

        <search-bar/>

        <list>
            <cell v-for="(item, i) in chatList" :key="i">
                <chat-item :data="item"/>
            </cell>
        </list>

        <tab-bar/>
    </scroller>
</template>
<style scoped>
    .page{
        flex: 1;
        justify-content: flex-start;
        padding-top: 90px;
        padding-bottom: 101px;
        background-color: #fbf9fe;
    }
</style>
<script>
import HeaderBar from '@/components/common/HeaderBar';
import TabBar from '@/components/common/TabBar';
import SearchBar from '@/components/common/SearchBar';
import ChatItem from '@/components/common/ChatItem';

export default {
  components: {
    HeaderBar,
    TabBar,
    SearchBar,
    ChatItem,
  },

  data() {
    return {
      chatList: [
        {
          avatar: 'http://sinacloud.net/vue-wechat/images/headers/header01.png',
          author: '阿荡',
          msg: '来呀 快活啊',
          time: '22:04',
        },
        {
          avatar: 'http://sinacloud.net/vue-wechat/images/headers/yehua.jpg',
          author: '收购万达讨论群',
          msg: '夜华: 我试一试',
          time: '22:04',
        },
        {
          avatar: 'http://sinacloud.net/vue-wechat/images/headers/header01.png',
          author: '收购淘宝讨论群',
          msg: '诸葛亮: 冒个泡',
          time: '22:04',
        },
        {
          avatar: 'http://sinacloud.net/vue-wechat/images/headers/sunquan.jpg',
          author: '孙权',
          msg: '孙权: 容我三思',
          time: '22:04',
          notice: 99,
        },
        {
          avatar: 'http://sinacloud.net/vue-wechat/images/headers/sunshangxiang.jpg',
          author: '孙尚香2',
          msg: '孙尚香: 夫君,身体要紧~ ',
          time: '22:04',
          notice: 1,
        },
        {
          avatar: 'http://sinacloud.net/vue-wechat/images/headers/guangyu.jpg',
          author: '关羽',
          msg: '关羽: 三姓家奴！ ',
          time: '22:04',
        },
      ],
    };
  },
  methods: {},
};
</script>
