<template>
  <footer id="footer">
    <div id="frame">
      <div class="frame">
        <div class="footer">
          <ul>
            <li><a href="about.html">About</a></li>
            <li><a href="contact.html">Contact</a></li>
            <li v-if="login && adminrole">
              <a href="#" v-on:click="servicePage()">Service</a>
            </li>
          </ul>
          <ul class="social-row">
            <li>
              <a href="https://github.com/my-github-profile">
                <i class="fab fa-github"></i>
              </a>
            </li>
            <li>
              <a href="https://twitter.com/my-twitter-profile">
                <i class="fab fa-twitter"></i>
              </a>
            </li>
            <li>
              <a href="https://www.linkedin.com/in/my-linkedin-profile">
                <i class="fab fa-linkedin"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer",

  data() {
    return {
      adminrole: false,
    };
  },

  computed: {
    login() {
      return this.$store.state.login;
    },
  },

  methods: {
    servicePage() {
      this.$router.push("/service.html");
    },
    hasRoleAdmin() {
      if (this.login && this.$store.state.user) {
        this.$store.state.user.roles.forEach((e) => {
          if (e === "ADMIN") {
            this.adminrole = true;
          }
        });
      }
    },
  },

  updated() {
    this.hasRoleAdmin();
  },
};
</script>

<style>
.footer {
  height: 60px;
  padding: 0 10px;
  display: flex;
  list-style: none;
  justify-content: space-between;
  align-items: center;
  background: #d96142;
}

#footer #frame {
  width: 990px;
  margin: 0 auto;
  margin-top: 5px;
  border-top: 1px solid #fff;
}

#footer .frame {
  width: 990px;
  background: #fff;
  margin: 0;
  border-radius: 3px;
  -webkit-border-radius: 3px;
  z-index: 9999;
}

.footer ul li {
  list-style: none;
  display: inline;
}

.footer a {
  margin: 0 15px;
  color: #fff;
  text-decoration: none;
}

.footer a:hover {
  padding-bottom: 5px;
  box-shadow: inset 0 -2px 0 0 #333;
}

.footer .social-row {
  font-size: 20px;
}

.footer .social-row li a {
  margin: 0 15px;
}
</style>
