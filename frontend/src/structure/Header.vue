<template>
  <header id="header">
    <div id="frame">
      <div class="frame">
        <div id="ust">
          <div id="logo">
            <a href="#">
              <img
                alt="Epey Logo"
                src="https://resim.epey.com/tema/logo.png"
                v-on:click="indexPage()"
              />
            </a>
          </div>
          <div id="search-box" class="search-key-box">
            <form action="  " method="get">
              <input
                aria-label="call"
                class="call general_call"
                type="text"
                value=""
                name="search"
                autocomplete="off"
                placeholder="sitede search"
              />
              <input type="hidden" name="floor" value="" />
              <input type="submit" class="search" value="" />
            </form>
            <div class="callconclusion general_conclusion"></div>
          </div>
          <div id="user_panel">
            <div class="panelliste">
              <div v-if="!login">
                <a href="#">
                  <span
                    class="sp1"
                    v-bind:style="!login ? 'line-height: 25px; padding-top: 6px;' : ''"
                    v-on:click="loginPage()"
                  >
                    Login
                  </span>
                </a>
              </div>
              <div v-if="login">
                <a href="#" v-on:click="logout()">
                  <span class="logout sp1">Logout</span>
                </a>
                <a href="#" v-on:click="userPage()">
                  <span class="profile sp1" >Profile</span>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

export default {
  name: "Header",
  components: {},

  data() {
    return {};
  },

  computed: {
    user() {
      return this.$store.state.user;
    },
    token() {
      return this.$store.state.token;
    },
    login() {
      return this.$store.state.login;
    },
  },

  methods: {
    loginPage() {
      this.$router.push("/login.html");
    },
    userPage() {
      this.$router.push("/user.html");
    },
    indexPage() {
      this.$router.push("/index.html");
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
#header #frame {
  width: 990px;
  margin: 0 auto;
  margin-top: 5px;
}

#header .frame {
  width: 970px;
  background: #fff;
  margin: 0;
  padding: 0 10px 10px;
  border-radius: 3px;
  -webkit-border-radius: 3px;
  z-index: 9999;
}

#header #ust {
  width: 970px;
  height: 40px;
  margin: 0 5px;
  padding: 10px 0 5px;
  background: #fff;
}

#header div {
  display: block;
}

#header #logo {
  float: left;
  margin: 3px 0 0 15px;
}

#header a {
  text-decoration: none;
  color: #000;
}

#header #search-box {
  float: left;
  width: 650px;
  height: 40px;
  border: 1px solid #d96142;
  border-radius: 3px;
  -webkit-border-radius: 3px;
  margin-left: 25px;
  background: #fff;
  margin-right: 5px;
  background: #e9e8e8;
}

#header .search-key-box {
  z-index: 1000;
}

#header script {
  display: none;
}

#header form {
  display: block;
  margin-top: 0em;
}

#header #search-box .call {
  width: 559px;
  font-size: 15px;
  margin-top: 4px;
  font: bold 13px/20px "Titillium Web", Arial;
}

#header #search-box .search {
  width: 70px;
  height: 38px;
  background: url(https://resim.epey.com/tema/aratmp.png) no-repeat center #d96142;
  cursor: pointer;
  margin: 1px 1px 0 0;
  padding: 5px 0;
  border-radius: 0 3px 3px 0;
}

#header #search-box input {
  float: left;
  padding: 7px 10px;
  border: none;
  background: 0 0;
  outline: 0 !important;
  border-radius: 3px;
  -webkit-border-radius: 3px;
}

#header #search-box .callconclusion {
  float: left;
  width: 580px;
  position: relative;
  z-index: 100000;
}

#header .panelliste {
  font-size: 15px;
  text-align: center;
}

#header #user_panel {
  position: relative;
  width: 102px;
  height: 36px;
  cursor: pointer;
  padding: 0 0 0 35px;
  border-radius: 3px;
  float: left;
  background: url(https://resim.epey.com/tema/user.png) 5px 10px no-repeat;
}

#header .panelliste .descikon {
  float: right;
  width: 25px;
  height: 17px;
  background: url(https://resim.epey.com/tema/down.png?v=3) no-repeat;
  margin: 10px 0 0;
}

#header .panelliste div {
  padding-top: 1px;
}

#header .panelliste span.sp1 {
  font-size: 16px;
  font-weight: 700;
}

#header .panelliste span {
  display: block;
  font-size: 12px;
  line-height: 17px;
}

#header .panelliste span {
  display: block;
  font-size: 12px;
  line-height: 17px;
}

#header .panelliste .logout.sp1 {
  padding-bottom: 3px;
}

#header .panelliste span:hover {
  padding-bottom: 2px;
  box-shadow: inset 0 -2px 0 0 #333;
}
</style>
