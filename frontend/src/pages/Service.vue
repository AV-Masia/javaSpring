<template>
  <Header></Header>
  <Navbar></Navbar>
  <main>
    <div class="content">
      <div class="wrapper">
        <div class="snap-drawers">
          <div id="authorisation">
            <div class="brace"></div>
            <form>
              <div id="authorisation_input" class="authorisation_input">
                <input
                  type="text"
                  id="limit"
                  class="member_mail membership_domain"
                  name="limit"
                  placeholder="limit"
                  v-model="limit"
                />
                <br />
                <div v-if="message != ''" class="message">{{ message }}</div>
                <button
                  type="button"
                  class="btn btn-primary authorisation epey-authorisation"
                  v-on:click="uploadMovies()"
                >
                  Upload movies
                </button>
                <button
                  type="button"
                  class="btn btn-primary authorisation epey-authorisation"
                  v-on:click="deleteMovies()"
                >
                  Clear movies
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
  <Footer></Footer>
</template>

<script>
import Header from "@/structure/Header";
import Footer from "@/structure/Footer";
import Navbar from "@/structure/Navbar";

import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

export default {
  name: "Login",
  components: {
    Header,
    Footer,
    Navbar,
  },

  data() {
    return {
      message: "",
      limit: "",
    };
  },

  computed: {
    user() {
      return this.$store.state.user;
    },
    token() {
      return this.$store.state.token;
    },
  },

  methods: {
    uploadMovies() {
      this.message = "";
      axios({
        method: "post",
        url: "/api/admin/upload_movies",
        params: {
          limit: this.limit,
        },
        headers: {
          user: JSON.stringify(this.user),
          Authorization: this.token,
        },
      })
        .then(() => {
          this.message = "Success upload movies";
        })
        .catch((error) => {
          console.log(error.response);
          this.message = error.response.data;
        });
    },
    deleteMovies() {
      axios
        .delete("/api/admin/delete_all_movies", {
          headers: {
            user: JSON.stringify(this.user),
            Authorization: this.token,
          },
        })
        .then(() => {
          this.message = "Clean All movies";
        })
        .catch((error) => {
          this.message = "Error";
          console.log(error.data);
        });
    },
  },
};
</script>

<style>
main {
  flex: 1 0 auto;
}

.content {
  flex: 1 1 auto;
}

main .content {
  width: 990px;
  margin: 0 auto;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>
