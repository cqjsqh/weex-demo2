<template>
    <div class="wrapper">
        <header-bar title="发现"/>

        <module-box class="gap">
            <module-item :data="{ icon: local('find_icon-circle.png'), text: '朋友圈'}"/>
        </module-box>

        <module-box class="gap">
            <module-item :data="{ icon: local('find_icon-qrcode.png'), text: '扫一扫'}"/>
            <module-item :data="{ icon: local('find_icon-shake.png'), text: '摇一摇'}"/>
        </module-box>

        <module-box class="gap">
            <module-item :data="{ icon: local('find_icon-shopping.png'), text: '购物'}"/>
            <module-item :data="{ icon: local('find_icon-moregame.png'), text: '游戏'}"/>
        </module-box>

        <tab-bar/>
    </div>
</template>
<style scoped>
    .wrapper{
        flex: 1;
        justify-content: flex-start;
        padding-top: 90px;
        padding-bottom: 101px;
        background-color: #fbf9fe;
    }
    .gap{
        margin-top: 36px;
    }
</style>
<script>
import HeaderBar from '@/components/common/HeaderBar';
import TabBar from '@/components/common/TabBar';
import ModuleBox from '@/components/common/ModuleBox';
import ModuleItem from '@/components/common/ModuleItem';

export default {
  components: {
    HeaderBar,
    TabBar,
    ModuleBox,
    ModuleItem,
  },

  data() {
    return {};
  },

  methods: {},
};
</script>
