<template>
  <Header></Header>
  <Navbar></Navbar>
  <main>
    <ListMovies></ListMovies>
  </main>
  <Footer></Footer>
</template>

<script>
import Header from "@/structure/Header";
import Footer from "@/structure/Footer";
import Navbar from "@/structure/Navbar";
import ListMovies from "@/components/ListMovies";

export default {
  name: "Index",
  components: {
    Header,
    Footer,
    Navbar,
    ListMovies,
  },
  computed: {
    user() {
      return this.$store.state.user;
    },
    token() {
      return this.$store.state.token;
    },
  },
  mounted() {
    const userData = JSON.parse(sessionStorage.getItem("userData"));
    const accessToken = sessionStorage.getItem("accessToken");
    const isLogin = sessionStorage.getItem("isLogin");
    this.$store.commit("setUser", userData);
    this.$store.commit("setToken", accessToken);
    this.$store.commit("setLogin", isLogin);
  },
};
</script>

<style>
main .content {
  width: 990px;
  margin: 0 auto;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>
