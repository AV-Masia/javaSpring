<template>
  <div class="hello" v-if=" ! user ||  Object.keys(user).length > 0 ">
    <h1>Wellcome User: {{ user.username }} </h1>
    <br>
    {{message}}
    <br>
    <button type="button" class="btn btn-primary" v-on:click="logout()"> Log Out </button>
  </div>
</template>

<script>

  import axios from "axios";
  axios.defaults.baseURL = 'http://localhost:8080';

  export default {
    name: 'Home',
    
    computed:{
      user(){ return this.$store.state.user },
      token(){ return this.$store.state.token },
    },

    data() {
          return {
              message: String,
          }
    },

    methods: {
      logout(){
        this.$store.commit('setUser'      , {} )
        this.$store.commit('setToken'     , '' )
        this.$router.push('/login')        
      },
    },

    beforeMount(){

        axios.get('/internal',
        {
          headers: {
            user: JSON.stringify(this.user),
            Authorization: this.token
          }
        })
          .then(
              (result) => {
                this.message = result.data;
              }
          )
          .catch(
            error => {
              console.log(error.data)
            }
          )
      },
      
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
