<template>
  <router-view></router-view>
</template>

<script>

export default {

    name: 'App',
    beforeMount(){
        this.$router.push('/index.html') 
    }

}
</script>

<style>

</style>