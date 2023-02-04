<template>
  <!-- Login Form -->
  <form>
    <div id="authorisation">
      <input
        type="text"
        id="firstName"
        class="user_first_name membership_domain"
        name="firstName"
        v-model="user.firstName"
        placeholder="First name"
      />
      <input
        type="text"
        id="lastName"
        class="user_last_name membership_domain"
        name="lastName"
        v-model="user.lastName"
        placeholder="Last name"
      />

      <input
        type="email"
        id="email"
        class="member_mail membership_domain"
        name="email"
        v-model="user.email"
        placeholder="Email"
      />
      <input
        type="password"
        id="password"
        class="user_password membership_domain"
        name="password"
        v-model="user.password"
        placeholder="Password"
      />
      <input
        type="password"
        id="confirmPassword"
        class="user_password_again membership_domain"
        name="confirmPassword"
        v-model="user.confirmPassword"
        placeholder="Password (At least 6 Characters)"
      />
      <button type="button" class="sign_up epey-authorisation" v-on:click="registration()">Registration</button>
    </div>
    
  </form>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

export default {
  name: "Registration",
  components: { 
    
  },

  data() {
    return {
      user: { firstName: "", lastName: "", email: "", password: "", confirmPassword: "" },
    };
  },

  methods: {
    registration() {
      axios
        .post("/api/registration", this.user)
        .then(() => {
          sessionStorage.setItem('firstName', this.user.firstName);
          sessionStorage.setItem('lastName', this.user.lastName);
          this.$router.push("/success.html");
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
        background: #D96142;
        color: #FFF;
    }

    #authorisation .inactive#input:hover {
        background: #D96142;
        color: #FFF;
    }

    #authorisation .active#registration {
        font-size: 18px;
        font-weight: 600;
        padding: 10px 16px;
        border: 1px solid #D96142;
        border-radius: 3px;
        background-color: #D96142;
        color: #FFF;
        cursor: default
    }

    #authorisation .inactive#input {
        font-size: 18px;
        font-weight: 600;
        padding: 10px 52.5px;
        border: 1px solid #D96142;
        cursor: pointer;
        border-radius: 3px
    }
</style>
