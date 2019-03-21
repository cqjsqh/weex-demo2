<template>
    <scroller class="page" show-scrollbar="false">
        <header-bar title="通讯录"/>

        <search-bar/>

        <div>
            <module-box>
                <module-item v-for="(item, i) in topContacts" :key="i" :data="item"/>
            </module-box>
        </div>

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
import ModuleBox from '@/components/common/ModuleBox';
import ModuleItem from '@/components/common/ModuleItem';

export default {
  components: {
    HeaderBar,
    TabBar,
    SearchBar,
    ModuleBox,
    ModuleItem,
  },

  data() {
    let local = this.local;
    return {
      topContacts: [
        { icon: local('contact_top-friend-notify.png'), text: '新的朋友', },
        { icon: local('contact_top-addgroup.png'), text: '群聊', },
        { icon: local('contact_top-tag.png'), text: '标签', },
        { icon: local('contact_top-offical.png'), text: '公众号', },
      ]
    }
  },
  methods: {},
};
</script>
