<template>
    <div>
        <MyClass/>
    </div>
</template>

<script>
    import MyClass from "@/views/homePage/myLearning/MyClass";

    export default {
        name: "Index",
        created() {


        },
        components: {MyClass},
        data() {
            return {
                active: 0
            }
        }
    }
</script>

<style scoped>
    body {
        height: 100%;
    }
</style>
