<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>
<style>
  body .el-table th.gutter {
    display: table-cell !important;
  }

  body {
    zoom: 90%;
    width: 100%;
  }
</style>
