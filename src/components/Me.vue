<template>
    <div class="wrapper">
        <header-bar title="我的"/>

        <module-box class="gap">
            <module-item :data="{ icon: local('me_more-my-album.png'), text: '相册'}"/>
            <module-item :data="{ icon: local('me_more-my-favorites.png'), text: '收藏'}"/>
            <module-item :data="{ icon: local('me_more-my-bank-card.png'), text: '钱包'}"/>
            <module-item :data="{ icon: local('me_my-card-package-icon.png'), text: '卡券'}"/>
        </module-box>

        <module-box class="gap">
            <module-item :data="{ icon: local('me_more-expression.png'), text: '表情'}"/>
        </module-box>

        <module-box class="gap">
            <module-item :data="{ icon: local('me_more-setting.png'), text: '设置'}"/>
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
