<template>
  <form>
    <div id="authorisation_input" class="authorisation_input">
      <div v-if="errorMessage != ''" class="message">{{ errorMessage }}</div>
      <input
        type="email"
        id="username"
        class="member_mail membership_domain"
        name="username"
        v-model="user.username"
        placeholder="username"
      />
      <br />
      <div v-if="errorMessageUsername != ''" class="message">
        {{ errorMessageUsername }}
      </div>
      <input
        type="password"
        id="password"
        class="user_password membership_domain"
        name="login"
        v-model="user.password"
        placeholder="password"
      />
      <br />
      <div v-if="errorMessagePassword != ''" class="message">
        {{ errorMessagePassword }}
      </div>
      <button
        type="button"
        class="btn btn-primary authorisation epey-authorisation"
        v-on:click="login()"
      >
        Log In
      </button>
    </div>
  </form>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

export default {
  name: "Login",
  components: {},

  data() {
    return {
      user: { username: "", password: "" },
      errorMessage: "",
      errorMessageUsername: "",
      errorMessagePassword: "",
    };
  },

  methods: {
    login() {
      this.errorMessage = "";
      this.errorMessageUsername = "";
      this.errorMessagePassword = "";
      axios
        .post("/api/login", this.user)
        .then((result) => {
          this.$store.commit("setUser", result.data[0]);
          this.$store.commit("setToken", result.data[1]);
          sessionStorage.setItem("userData", JSON.stringify(result.data[0]));
          sessionStorage.setItem("accessToken", result.data[1]);
          sessionStorage.setItem("isLogin", true);
          this.$router.push("/index.html");
        })
        .catch((error) => {
          console.log(error.data);
          if (error.response.status == 403) {
            this.errorMessage = error.response.data;
          }
          if (error.response.status == 400) {
            error.response.data.errors.forEach((e) => {
              if (e.field == "username") {
                this.errorMessageUsername = e.defaultMessage;
              }
              if (e.field == "password") {
                this.errorMessagePassword = e.defaultMessage;
              }
            });
          }
        });
    },
  },
};
</script>

<style>
#authorisation .active#input:hover {
  background: #d96142;
  color: #fff;
}

#authorisation .inactive#registration:hover {
  background: #d96142;
  color: #fff;
}

#authorisation .active#input {
  font-size: 18px;
  font-weight: 600;
  padding: 10px 52.5px;
  border: 1px solid #d96142;
  border-radius: 3px;
  background-color: #d96142;
  color: #fff;
  cursor: default;
}

#authorisation .inactive#registration {
  font-size: 18px;
  font-weight: 600;
  padding: 10px 16px;
  border: 1px solid #d96142;
  cursor: pointer;
  border-radius: 3px;
  margin: 5px;
}
</style>
