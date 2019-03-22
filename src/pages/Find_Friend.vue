<template>
    <div>
        <header-bar title="新页面"/>
    </div>
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

export default {
  components: {
    HeaderBar,
  },

  data() {
    return {};
  },

  methods: {},
};
</script>
