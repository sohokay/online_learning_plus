<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name||getUserName }}</div>
    <el-button @click="getListCity()">权限测试</el-button>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'
  import request from '@/utils/request'
  import {getUserName} from  '../../utils/setCookies'
  export default {
    name: 'Dashboard',
    computed: {
      ...mapGetters([
        'name'
      ]),
      getUserName
    },
    methods: {
      getListCity() {
        request({
          url: "http://localhost:8080/ajax/getCityList",
          method: 'get'
        }).then(res => (
          console.log(res)
        ))
      }
    }
  }
</script>

<style lang="scss" scoped>
  .dashboard {
    &-container {
      margin: 30px;
    }

    &-text {
      font-size: 30px;
      line-height: 46px;
    }
  }
</style>
