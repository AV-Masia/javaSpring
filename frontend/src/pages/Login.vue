<template>
  <Header></Header>
  <Navbar></Navbar>
  <main>
    <div class="content">
      <div class="wrapper">
        <div class="snap-drawers">
          <div id="authorisation">
            <div class="butonlar">
              <span
                id="input"
                :class="showLogin ? 'active' : 'inactive'"
                v-on:click="showPanelLogin"
                >Login</span
              >
              <span
                id="registration"
                :class="!showLogin ? 'active' : 'inactive'"
                v-on:click="showPanelRegistration"
              >
                Registration
              </span>
            </div>
            <div class="brace"></div>
            <SignIn v-if="showLogin"></SignIn>
            <Registration v-if="!showLogin"></Registration>
            <div v-if="showLogin" class="forgot_password">
              <div class="brace"></div>
              <a
                style="float: none"
                class="password_forget authorisation epey-authorisation"
                href="/forgetPassword.html"
              >
                I forgot my password
              </a>
              <br />
            </div>
          </div>
        </div>
        <div id="conclusion"></div>
      </div>
    </div>
  </main>
  <Footer></Footer>
</template>

<script>
import Header from "@/structure/Header";
import Footer from "@/structure/Footer";
import Navbar from "@/structure/Navbar";
import SignIn from "@/components/SignIn";
import Registration from "@/components/Registration";

export default {
  name: "Login",
  components: {
    Header,
    Footer,
    Navbar,
    SignIn,
    Registration,
  },

  data() {
    return {
      showLogin: true,
    };
  },
  methods: {
    showPanelLogin() {
      this.showLogin = true;
    },
    showPanelRegistration() {
      this.showLogin = false;
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

#authorisation {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: inherit;
  min-height: calc(100vh - 390px);
  padding-top: 10px;
  float: left;
  width: 100%;
}

#authorisation .butonlar {
  text-align: center;
  padding: 10px 0px;
  margin-bottom: 5px;
}

#authorisation .brace {
  border-top: 1px solid #d96142;
  width: 300px;
  margin: 10px 0;
}

#authorisation .forgot_password {
  display: contents;
  text-align: center;
}

#authorisation .authorisation_input {
  text-align: center;
  flex-wrap: wrap;
}

#authorisation .message {
  float: none;
  width: auto;
  text-align: inherit;
  padding: 20px 0 0 0;
  width: 300px;
}

.message {
  float: left;
  width: 100%;
  text-align: center;
}

#authorisation .membership_domain {
  border: 1px solid #d96142;
  width: 300px;
  height: 50px;
  padding: 15px;
  font-size: 15px;
  font-weight: 500;
  margin: 3px 0;
  box-sizing: border-box;
  border-radius: 3px;
  -webkit-border-radius: 3px;
}

#authorisation .epey-authorisation {
  background-color: #d96142;
  border-radius: 3px;
  -webkit-border-radius: 3px;
}

#authorisation .authorisation,
#authorisation .sign_up {
  width: 300px;
  padding: 15px 10px;
  font-size: 15px;
  font-weight: 500;
  margin: 3px 0;
  box-sizing: border-box;
  border: none;
  color: #fff;
  cursor: pointer;
}

label {
  cursor: default;
}

#authorisation .password_forget {
  margin: 5px 0;
}

a {
  text-decoration: none;
  color: #000;
}

input {
  writing-mode: horizontal-tb !important;
  text-rendering: auto;
  color: fieldtext;
  letter-spacing: normal;
  word-spacing: normal;
  line-height: normal;
  text-transform: none;
  text-indent: 0px;
  text-shadow: none;
  display: inline-block;
  text-align: start;
  appearance: auto;
  -webkit-rtl-ordering: logical;
  cursor: text;
  background-color: field;
  margin: 0em;
  padding: 1px 2px;
  border-width: 2px;
  border-style: inset;
  border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
  border-image: initial;
}

button {
  appearance: auto;
  writing-mode: horizontal-tb !important;
  text-rendering: auto;
  letter-spacing: normal;
  word-spacing: normal;
  line-height: normal;
  text-transform: none;
  text-indent: 0px;
  text-shadow: none;
  display: inline-block;
  text-align: center;
  align-items: flex-start;
  cursor: default;
  box-sizing: border-box;
  margin: 0em;
  padding: 1px 6px;
  border-width: 2px;
  border-style: outset;
  border-color: buttonborder;
  border-image: initial;
}

.error {
  color: red;
}
</style>
