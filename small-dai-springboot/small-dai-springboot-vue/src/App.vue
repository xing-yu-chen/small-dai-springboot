<template>
  <div id="app">
    <smallDaiHeader></smallDaiHeader>
    <router-view/>
    <small-dai-footer></small-dai-footer>
  </div>
</template>
<script>
import SmallDaiHeader from '../src/components/aside/Header.vue'
import SmallDaiFooter from '../src/components/aside/Footer.vue'
export default {
  name: 'app',
  components: {
    SmallDaiHeader,
    SmallDaiFooter
  }
}
</script>

<style scoped>
#app {
  width: 100%;
  height: 100vh;
}
</style>
