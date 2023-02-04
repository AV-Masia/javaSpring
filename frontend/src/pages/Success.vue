<template>
  <Header></Header>
  <Navbar></Navbar>
  <main>
    <form>
      <div class="authorisation_success">
        <p>Hello, {{ firstName }} {{ lastName }}</p>
        <br />
        <p>Registration completed successfully.</p>
        <br />
        <p>A letter was sented to your email.</p>
        <br />
        <button
          type="button"
          class="btn btn-primary authorisation success return to login"
          v-on:click="loginPage()"
        >
          Login
        </button>
      </div>
    </form>
  </main>
  <Footer></Footer>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

import Header from "@/structure/Header";
import Footer from "@/structure/Footer";
import Navbar from "@/structure/Navbar";

export default {
  name: "Seccess",
  components: {
    Header,
    Footer,
    Navbar,
  },

  data() {
    return {
      firstName: "",
      lastName: "",
    };
  },

  mounted() {
    this.firstName = sessionStorage.getItem("firstName");
    this.lastName = sessionStorage.getItem("lastName");
  },

  methods: {
    loginPage() {
      this.$router.push("/login.html");
    },
  },
};
</script>

<style>
.authorisation_success {
  margin: 15px;
  text-align: center;
  font-size: xx-large;
}

.btn {
  font-size: 18px;
  font-weight: 600;
  padding: 10px 16px;
  border: 1px solid #d96142;
  border-radius: 3px;
  background-color: #d96142;
  color: #fff;
  cursor: default;
  width: 300px;
}
</style>
