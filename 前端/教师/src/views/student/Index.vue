<template>
  <div class="app-container">
    <StudentList/>
  </div>
</template>

<script>
  import StudentList from "@/views/student/StudentList";

  export default {
    name: "Index",
    components: {StudentList}
  }
</script>

<style scoped>

</style>
