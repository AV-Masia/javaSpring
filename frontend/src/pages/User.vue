<template>
  <Header></Header>
  <Navbar></Navbar>
  <main>
    <form>
      <div id="authorisation">
        <div></div>

        <div class="message">{{ this.profile.firstName }}</div>
        <input
          type="text"
          id="firstName"
          class="user_first_name membership_domain"
          name="firstName"
          v-model="userUpdate.firstName"
          placeholder="First name"
        />
        <div class="message">{{ this.profile.lastName }}</div>
        <input
          type="text"
          id="lastName"
          class="user_last_name membership_domain"
          name="lastName"
          v-model="userUpdate.lastName"
          placeholder="Last name"
        />
        <div class="message">{{ this.profile.email }}</div>
        <input
          type="email"
          id="email"
          class="member_mail membership_domain"
          name="email"
          v-model="userUpdate.email"
          placeholder="Email"
        />
        <input
          type="password"
          id="password"
          class="user_password membership_domain"
          name="password"
          v-model="userUpdate.password"
          placeholder="Password"
        />
        <input
          type="password"
          id="confirmPassword"
          class="user_password_again membership_domain"
          name="confirmPassword"
          v-model="userUpdate.confirmPassword"
          placeholder="Password (At least 6 Characters)"
        />
        <button type="button" class="sign_up user-authorisation" v-on:click="update()">
          Update
        </button>
        <button
          type="button"
          class="sign_up user-authorisation"
          v-on:click="deleteUser()"
        >
          Delete
        </button>
      </div>
    </form>
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
  name: "User",
  components: {
    Header,
    Footer,
    Navbar,
  },

  mounted() {
    const userData = JSON.parse(sessionStorage.getItem("userData"));
    const accessToken = sessionStorage.getItem("accessToken");
    const isLogin = sessionStorage.getItem("isLogin");
    this.$store.commit("setUser", userData);
    this.$store.commit("setToken", accessToken);
    this.$store.commit("setLogin", isLogin);
  },
  created() {
    this.getUserByName();
  },
  computed: {
    profile: function () {
      return this.$store.state.profile;
    },
    user() {
      return this.$store.state.user;
    },
    token() {
      return this.$store.state.token;
    },
    userUpdate() {
      return {
        id: this.profile.id,
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        confirmPassword: "",
      };
    },
  },

  methods: {
    async getUserByName() {
      await axios({
        method: "get",
        url: "/api/user",
        params: {
          userName: this.user.username,
        },
        headers: {
          user: JSON.stringify(this.user),
          Authorization: this.token,
        },
      })
        .then((result) => {
          this.$store.commit("setProfile", result.data);
        })
        .catch((error) => {
          console.log(error.data);
        });
    },
    update() {
      axios
        .post("/api/user", this.userUpdate, {
          headers: {
            user: JSON.stringify(this.user),
            Authorization: this.token,
          },
        })
        .then((result) => {
          this.$store.commit("setProfile", result.data);
          return this.getUserByName();
        })
        .catch((error) => {
          console.log(error.data);
        });
    },
    deleteUser() {
      axios({
        method: "delete",
        url: "/api/deleteUser",
        params: {
          id: this.profile.id,
        },
        headers: {
          user: JSON.stringify(this.user),
          Authorization: this.token,
        },
      })
        .then(() => {
          this.logout();
        })
        .catch((error) => {
          console.log(error.data);
        });
    },

    logout() {
      axios
        .post("/api/logout")
        .then(() => {
          this.$store.commit("setUser", {});
          this.$store.commit("setToken", "");
          this.$store.commit("setLogin", false);
          sessionStorage.clear();
          this.$router.push("/index.html");
        })
        .catch((error) => {
          console.log(error.data);
        });
    },
  },
};
</script>

<style>
#authorisation .active#registration:hover {
  background: #d96142;
  color: #fff;
}

#authorisation .inactive#input:hover {
  background: #d96142;
  color: #fff;
}

#authorisation .active#registration {
  font-size: 18px;
  font-weight: 600;
  padding: 10px 16px;
  border: 1px solid #d96142;
  border-radius: 3px;
  background-color: #d96142;
  color: #fff;
  cursor: default;
}

#authorisation .inactive#input {
  font-size: 18px;
  font-weight: 600;
  padding: 10px 52.5px;
  border: 1px solid #d96142;
  cursor: pointer;
  border-radius: 3px;
}
</style>
